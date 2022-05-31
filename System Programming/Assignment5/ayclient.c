#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/stat.h>

int main(int argc, char *argv[]){  
	int fd1;
	int fd2;
	char ch;
	char reply[30];
	
	while((fd1=open("/tmp/server", O_WRONLY))==-1){  
		fprintf(stderr, "trying to connect\n");  
		sleep(1);
	}
	
	fd2 = open("/tmp/server1", O_RDONLY);
	
	printf("Connected: type in data to be sent\n");  
	while((ch=getchar()) != -1){ // -1 is CTRL-D
		write(fd1, &ch, 1);
		read(fd2, reply, sizeof(reply));
		printf("%s\n", reply);		  
	}	
	close(fd1);
	close(fd2);
}

