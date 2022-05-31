#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/stat.h>
#include<time.h>
#include<signal.h>

void player(char *s, const char *path, int fd);

int main() {
    // start up log
    printf("This is a 2-player game with a referee\n");

    // setting up fifo
    int fd1, fd2;
    char ch1;
    char ch2;
    mkfifo("/tmp/p1", 0666);
    mkfifo("/tmp/p2", 0666);

    // starting child processes
    pid_t player1;
    pid_t player2;

    player1 = fork();
    if(!player1){
        player("Player1", "/tmp/p1", fd1);
    } 
    
    player2 = fork();
    if(!player2){
        player("Player2", "/tmp/p2", fd2);
    }    

    // game loop
    while(1) {
        // starting player1
        printf("Referee: Player1 plays\n");
        fd1 = open("/tmp/p1", O_WRONLY);
        write(fd1, &ch1, 1);
        close(fd1);

        // waiting for player1
        fd1 = open("/tmp/p1", O_RDONLY);
        read(fd1, &ch1, 1);
        close(fd1);

        // starting player2
        printf("Referee: Player2 plays\n");
        fd2 = open("/tmp/p2", O_WRONLY);
        write(fd2, &ch2, 1);
        close(fd2);

        // waiting for player2
        fd1 = open("/tmp/p2", O_RDONLY);
        read(fd2, &ch2, 1);
        close(fd2);
    }

    return 0;
}

void player(char *s, const char *path, int fd) {

    // setting up fifo
    char ch;

    // setting up dice and points
    int dice;
    int points = 0;
    long int ss=0;

    // game loop
    while(1) {
        // waiting for parent
        fd = open(path, O_RDONLY);
        read(fd, &ch, 1);
        close(fd);

        // playing dice
        printf("%s: playing my dice\n", s);
        srand(time(NULL));
        dice = (rand() % 10) + 1;
        printf("%s: got %d dice\n", s, dice);
        points += dice;
        printf("%s: Total so far %d\n\n", s, points);

        if(points >= 50) {
            	printf("%s: game over I won\n", s);
		kill(0, SIGTERM);
        }

        // sending control back to parent
        sleep(5); // to slow down the execution
        fd = open(path, O_WRONLY);
        write(fd, &ch, 1);
        close(fd);
    }
}


