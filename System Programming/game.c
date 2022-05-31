#include <fcntl.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

// gets set in signal handler
int usr_interrupt = 0;

// signal handler for SIGUSR1
void signalHandler(int sigNum) {
    signal(SIGUSR1, signalHandler);
    usr_interrupt = 1;
}

void child1(const char *name,
            const char *fifoPathname) {
    // seeding pseudo random number generator
    srand(getpid() * time(NULL));

    // setting up fifo
    int fifoFd;
    char fifoData;

    // setting up dice and points
    int dice;
    int points = 0;

    // game loop
    while(1) {
        // waiting for parent
        fifoFd = open(fifoPathname, O_RDONLY);
        read(fifoFd, &fifoData, 1);
        close(fifoFd);

        // playing dice
        printf("%s: playing my dice\n", name);
        dice = rand() % 10 + 1;
        printf("%s: got %d dice\n", name, dice);
        points += dice;
        printf("%s: Total so far %d\n\n", name, points);

        if(points >= 50) {
            printf("%s: game over I won\n", name);
            kill(0, SIGTERM);
        }

        // sending control back to parent
        sleep(5); // to slow down the execution
        fifoFd = open(fifoPathname, O_WRONLY);
        write(fifoFd, &fifoData, 1);
        close(fifoFd);
    }
}

void child2(const char *name,
            int pipeChildReadFd[2],
            int pipeChildWriteFd[2]) {
    // seeding pseudo random number generator
    srand(getpid() * time(NULL));

    // setting up pipes
    char pipeData;
    close(pipeChildReadFd[1]);
    close(pipeChildWriteFd[0]);

    // setting up dice and points
    int dice;
    int points = 0;

    // game loop
    while(1) {
        // waiting for parent
        read(pipeChildReadFd[0],
             &pipeData,
             1);

        // playing dice
        printf("%s: playing my dice\n", name);
        dice = rand() % 10 + 1;
        printf("%s: got %d dice\n", name, dice);
        points += dice;
        printf("%s: Total so far %d\n\n", name, points);

        if(points >= 50) {
            printf("%s: game over I won\n", name);
            kill(0, SIGTERM);
        }

        // sending control back to parent
        sleep(5); // to slow down the execution
        write(pipeChildWriteFd[1],
              &pipeData,
              1);
    }
}

void child3(const char *name) {
    // seeding pseudo random number generator
    srand(getpid() * time(NULL));

    // setting up signals
    signal(SIGUSR1, signalHandler);

    // setting up dice and points
    int dice;
    int points = 0;

    // game loop
    while(1) {
        // waiting for parent
        while(!usr_interrupt);
        usr_interrupt = 0;

        // playing dice
        printf("%s: playing my dice\n", name);
        dice = rand() % 10 + 1;
        printf("%s: got %d dice\n", name, dice);
        points += dice;
        printf("%s: Total so far %d\n\n", name, points);

        if(points >= 50) {
            printf("%s: game over I won\n", name);
            kill(0, SIGTERM);
        }

        // sending control back to parent
        sleep(5); // to slow down the execution
        kill(getppid(), SIGUSR1);
    }
}

int main() {
    // start up log
    printf("This is a 3-player game with a referee\n");

    // setting up fifo
    const char *fifoPathname = "fifo";
    int fifoFd;
    mode_t fifoMode = 0666;
    char fifoData;
    mkfifo(fifoPathname, fifoMode);

    // setting up pipes
    int pipeParentReadFd[2];
    int pipeParentWriteFd[2];
    char pipeData;
    pipe(pipeParentReadFd);
    pipe(pipeParentWriteFd);

    // setting up signals
    signal(SIGUSR1, signalHandler);

    // starting child processes
    pid_t pidChild1;
    pid_t pidChild2;
    pid_t pidChild3;

    pidChild1 = fork();
    if(!pidChild1)
        child1("Child1",
               fifoPathname);

    pidChild2 = fork();
    if(!pidChild2)
        child2("Child2",
               pipeParentWriteFd,
               pipeParentReadFd);

    pidChild3 = fork();
    if(!pidChild3)
        child3("Child3");

    // close unused read and
    // write ends of pipes
    close(pipeParentReadFd[1]);
    close(pipeParentWriteFd[0]);

    // game loop
    while(1) {
        // starting child1
        printf("\nReferee: Child1 plays\n\n");
        fifoFd = open(fifoPathname, O_WRONLY);
        write(fifoFd, &fifoData, 1);
        close(fifoFd);

        // waiting for child1
        fifoFd = open(fifoPathname, O_RDONLY);
        read(fifoFd, &fifoData, 1);
        close(fifoFd);

        // starting child2
        printf("\nReferee: Child2 plays\n\n");
        write(pipeParentWriteFd[1],
              &pipeData,
              1);

        // waiting for child2
        read(pipeParentReadFd[0],
             &pipeData,
             1);

        // starting child3
        printf("\nReferee: Child3 plays\n\n");
        kill(pidChild3, SIGUSR1);

        // waiting for child3
        while(!usr_interrupt);
        usr_interrupt = 0;
    }

    return 0;
}