#include<stdio.h> 
#include<stdlib.h>
#include<unistd.h> 
#include<fcntl.h>

int main(int argc, char *argv[]){  
   int fd = open(argv[1], O_RDONLY);
   char c;
	
   if(fd < 0){
       exit(1);
   }	

   while(read(fd, &c, 1) == 1){  
	printf("%c", c);
   }	

   close(fd);
   exit(0);
}

