#include<stdio.h> 
#include<stdlib.h>
#include<unistd.h> 
#include<fcntl.h>
#include<string.h>

int main(int argc, char *argv[]){ 
   int fd = open(argv[argc-1], O_WRONLY | O_CREAT | O_TRUNC, 0644);
   int f;
   char c[400];
   char buffer;
   int n = 0;
	
   if(fd < 0){
       exit(1);
   }
	
   for(int i = 1; i<argc-1; i++){
       f = open(argv[i], O_RDONLY);
       while(read(f, &buffer, 1) == 1){
           c[n] = buffer;
           n++;
       }
       close(f);    
   }
   
   write(fd, c, strlen(c));	

   close(fd);
   exit(0);
}

