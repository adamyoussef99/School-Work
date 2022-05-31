#include <stdio.h>
#include <stdlib.h>
#include <signal.h> 
#include <unistd.h>
#include <sys/types.h>

// handler for SIGALRM
void alarm_handler(int sig) { 
	printf("Ding!\n");
	printf("Done!\n");
	exit(0);
}

int main(int argc, char *argv[]) {   
	int sleeper = atoi(argv[1]);
	
	printf("alarm application starting\n");
	
	pid_t pid = fork();
	if(pid == 0){
		// child process
		printf("waiting for alarm to go off\n");
		//sleep for as long as was put in command line
		sleep(sleeper);
		//after sleeping, send signal to SIGALRM
		kill(getppid(), SIGALRM);
	}
	else{
		// parent process
		//signals alarm and calls alarm handler function
		signal(SIGALRM, alarm_handler);
		while(1);	
	}
	
} 
