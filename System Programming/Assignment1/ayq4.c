#include<stdio.h> 
#include<stdlib.h>
#include<unistd.h> 
#include<fcntl.h> 

int main(int argc, char *argv[]) {
	  
    int fd = open(argv[1], O_RDONLY);
    int count = 0;
    char buffer;
    
    if(fd < 0){
    	exit(1);
    }
    
    while(read(fd, &buffer, 1) == 1){
    	if(buffer == '\n'){
    	    count++;
    	}
    }

	  
    printf("Number of lines in the file: %d\n", count);
    
    close(fd);
	  
    exit(0);   
}
