#include <stdio.h>
#include <stdlib.h>
#include <signal.h> 
#include <unistd.h>
#include <sys/types.h>
#include <fcntl.h>
#include <string.h>

// handler for SIGALRM
void alarm_handler(int sig) { 
	printf("Process is killed.\n");
}

int main(int argc, char *argv[]) {
	
	int fd = open(argv[1], O_WRONLY |O_CREAT | O_TRUNC, 0644);
	
	printf("Parent opened file: %s\n", argv[1]);
	
	if(fd < 0){
		exit(1);
	}
	
	char buf[3][100] = {"EXAM! EXAM! EXAM!\n", "HELP! HELP! HELP!\n", "STUDY! STUDY! STUDY!\n"};
	
	//2 children to be made
	int pid1;
	int pid2;
	
	//make first child
	if(pid1 = fork()){
		printf("Parent created child process: %d\n", pid1);
		//write first string to file
		write(fd, buf[0], strlen(buf[0]));
		printf("%d has written to the file: %s", pid1, buf[0]);
		sleep(5);
		//kill when SIGALRM is signaled
		kill(pid1, SIGALRM);
		
	}
	//make second child
	else if(pid2 = fork()){
		printf("Parent created child process: %d\n", pid2);
		//signal SIGALRM to kill first child process
		signal(SIGALRM, alarm_handler);
		
		//write second string to file
		write(fd, buf[1], strlen(buf[1]));
		printf("%d has written to the file: %s", pid2, buf[1]);
		sleep(5);
		
		//kill when SIGALRM is signaled
		kill(pid2, SIGALRM);
	
	}
	else{
		//signal SIGALRM to kill first child process
		signal(SIGALRM, alarm_handler);
		
		//write final string to file
		write(fd, buf[2], strlen(buf[2]));
		printf("Parent has written to the file: %s", buf[2]);
		
		//sleep then close file
		sleep(5);
		close(fd);
		
		printf("Parent has closed the file.\n");
	}
	
	exit(0);
}

