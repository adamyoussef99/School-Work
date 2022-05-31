#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>

#define MAX_CHARS 10000

int main(int argc, char **argv) {
	//pipe descriptors
	int fd[2];
	
	//checking if pipe has an error 
	if (pipe(fd) == -1) {
		perror("pipe call");
		exit(1);
	}
	
	//make child process
	int pid = fork();
	
	//child
	if(pid == 0){
		close(fd[0]);
		
		//open file and run command on command line
		FILE *fp;
		fp = popen(argv[1], "r");
		char command[MAX_CHARS];	//stores the command
		
		//while the result of the command is writing to the pipe
		while(fgets(command, sizeof(command), fp) != NULL){
			//write command to pipe
			write(fd[1], command, strlen(command) + 1);
		}
		//close file and pipe
		pclose(fp);
		close(fd[1]);
		
	}

	else{
		close(fd[1]);
		char reader[MAX_CHARS];		//holds command result read from pipe
		
		int bytes = 0;		//#of bytes while writing to file
		int total = 0;		//total # of bytes
		
		printf("Parent process...\n");
		
		//create new file 'result.txt'
		FILE *fp = fopen("result.txt", "w");
		
		//counts total bytes as it writes to file
		while((bytes = read(fd[0], reader, MAX_CHARS)) > 0){
			total += bytes;
			fputs(reader, fp);
		}
		printf("Total number of bytes: %d\n", total);
       		
       		//close file 'result.txt' and pipe
       		fclose(fp);
       		close(fd[0]);
       		
       		//run the command line argument
       		execlp(argv[1], argv[1], NULL);
	}
	
	exit(0);
}



























