#include<stdlib.h>
#include<stdio.h>
#include<fcntl.h>
#include<unistd.h>
#include<string.h>

int main(int argc, char *argv[]){  

   int fd = open("list1.txt", O_WRONLY |O_CREAT |O_TRUNC, 0644);

   if(fd<0){
       exit(1);
   }
       
   write(fd,"101   GM\tBuick\t2010\n102   Ford\tLincoln\t2005", strlen("101   GM\tBuick\t2010\n102   Ford\tLincoln\t2005"));
    
   close(fd);
}
