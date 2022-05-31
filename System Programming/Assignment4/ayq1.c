#include<stdio.h> 
#include<signal.h> 
#include <unistd.h>

// handler for SIGINT
void handler(int sig) { 
	printf("Ctrl + c pressed \n"); 
} 

int main() { 

	//when user press ctrl + c, run the handler function
	signal(SIGINT, handler); 

	//when user press ctrl + z ignore the press
	signal(SIGTSTP, SIG_IGN); 


	pid_t pid = fork();
	if(pid == 0){
		// child process
		// runs donothing binary file
		execl("./donothing", "donothing", NULL);

	}
	else{
		// parent process
		printf("I am the parent process\n");
		kill(pid,SIGINT);
		sleep(10);
	}
	return 0; 
} 

//when we go back to the parent, I kill the signal to SIGINT which calls the handler function. Therefore, the functionality of ctrl + c is restored.
