#include<stdlib.h>
#include<stdio.h>
#include<fcntl.h>
#include<unistd.h>
#include<string.h>

int main(int argc, char *argv[]){  

   int fd = open(argv[1], O_RDONLY | O_APPEND);
   char buffer;
   char append[100];

   if(fd<0){
       exit(1);
   }
       
   while(read(fd, &buffer, 1) == 1){
       printf("%c", buffer);
   }
   
   close(fd);
   
   fd = open(argv[1], O_RDWR | O_APPEND);
   
   printf("\nWhat would you like to add?\n");
   scanf("%s", &append);
   getchar();
  
   char newLine = '\n';
   strncat(append, &newLine, 1);
   
   while(read(fd, &buffer, 1) == 1){
       printf("%c", buffer);
   }
   printf("%s", &append);
   write(fd, append, strlen(append));
    
   close(fd);
}
