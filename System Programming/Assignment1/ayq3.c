#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]){  

   FILE *fp = fopen("list1.txt", "w");
       
   char list[] = "101   GM\tBuick\t2010\n102   Ford\tLincoln\t2005";
   int length = strlen(list);
   for(int i=0; i<length; i++){
   	fputc(list[i], fp);
   } 
    
   fclose(fp);
   return(0);
}
