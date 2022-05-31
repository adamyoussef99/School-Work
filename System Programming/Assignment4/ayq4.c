#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
	//pipe descriptors
	int fd[2];
	//used for reading random numbers from pipe
	int display[1];
	
	//checking if pipe has an error 
	if (pipe(fd) == -1) {
		perror("pipe call");
		exit(1);
	}
	
	//makes 2 children
	int pid1 = fork();
	int pid2 = fork();
	//write 5 random numbers to pipe for both children
	if(pid1 == 0){
		//loop through and create 5 random numbers and write to pipe
		for(int i = 0; i < 5; i++){
			int num1 = rand() % 100;
			
			write(fd[1], &num1, sizeof(num1));
		}
	}
	else if(pid2 == 0){
		for(int i = 0; i < 5; i++){
			int num2 = rand() % 100;
			
			write(fd[1], &num2, sizeof(num2));
		}
	}
	else{
		printf("Parent process...\n");
		printf("Reading random numbers...\n");
		//loop and read all 10 random numbers written to pipe from children
		for (int i = 0; i < 10; i++){
			// Read the numbers and put them on the screen.
			read(fd[0],display, sizeof(display));
			printf("Random number %d: %d\n", i+1, display[0]);
		}
	}
	
	exit(0);
}

