#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(int where, char **cline){
	int signal;
	//if there is only one Unix command
	if(where-1 == 1){
		if(fork() == 0){
		
			printf("Process ID: %d\n", getpid());
			execlp(cline[1], cline[1], NULL, (char *) NULL);
			printf("\n");
			exit(1);
			
		}
		
		wait(&signal);	
	}
	
	//if there is more than 1 argument
	else if(where-1 > 1){
	
		//go through command line Unix commands
		for(int i = 1; i < where-1; i++){
		
			//if the fork is successful
			if(fork()==0){
			
				//print process ID
				printf("Process ID: %d\n", getpid());
				
				//if we are at the last Unix command
				if(i==where-1){
				
					//run the last Unix command, third param is null because there are no arguments
					execlp(cline[i], cline[i], NULL, (char *) NULL);
					printf("\n");
					
				}
				
				else{
				
					//Run the current Unix command with it's argument
					execlp(cline[i], cline[i], cline[i+1], (char *) NULL);
					printf("\n");
					
				}
				
				exit(1);
			}
					
		}
		
		for(int i = 0; i < where-1; i++){
			wait(&signal);
		}
	}
	
	exit(1);
}
