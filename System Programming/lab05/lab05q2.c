#include<stdlib.h>
#include<stdio.h>
#include<fcntl.h>
#include<unistd.h>
#include<string.h>

#include <sys/types.h>
#include <sys/wait.h>

int main(int argc, char* argv[])
{  
    int fd = open("test.txt", O_WRONLY |O_CREAT |O_TRUNC, 0644);;

    int pid = fork();
    
    if(pid == -1){
    	perror("fork");
    }
    
    else if(pid == 0) {
    	printf("I am the child %d now writing to file\n",getpid());  
    	
    	//write to file as child and close fd
        write(fd, "hello this is the child\n", strlen("hello this is the child\n"));
        close (fd);
    }
    
    else{
        printf("I am the parent %d\n",getpid());
        
        int status;
        wait(&status);
        
        //write to child as parent and close fd
        write(fd, "hello this is the parent\n", strlen("hello this is the parent\n"));
        close (fd);
     
    }
  
    return 0;
}
