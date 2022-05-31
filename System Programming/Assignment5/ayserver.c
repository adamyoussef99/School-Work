#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/stat.h>

// This is the server , server.c
int main(int argc, char *argv[]){  
	int fd1, fd2;
	char ch;
	char reply[30] = "I have received your message.";
	unlink("/tmp/server"); // delete it if it exists
	unlink("/tmp/server1"); // delete it if it exists  
	if(mkfifo("/tmp/server", 0777)!=0){
		exit(1);
	}
	if(mkfifo("/tmp/server1", 0777)!=0){
		exit(1);
	}	

	while(1){
		fprintf(stderr, "Waiting for a client\n");  
		fd1 = open("/tmp/server", O_RDONLY);
		fd2 = open("/tmp/server1", O_WRONLY);  
		fprintf(stderr, "Got a client: ");  
		while(read(fd1, &ch, 1) == 1){
			fprintf(stderr, "%c", ch);
			write(fd2, reply, 30);
		}
				
	}
}
