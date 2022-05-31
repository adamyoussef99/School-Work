#include<stdlib.h>
#include<stdio.h>
#include<fcntl.h>
#include<unistd.h>
#include<string.h>

int main(int argc, char *argv[]){  

   FILE *fp = fopen("test.txt", "r");
   char buffer;
   char append[100];
       
   while((buffer = getc(fp)) != EOF){
       printf("%c", buffer);
   }
   fclose(fp);
   
   fp = fopen("test.txt", "a+");
   
   printf("\nWhat would you like to add?\n");
   scanf("%s", &append);
   getchar();
  
   char newLine = '\n';
   strncat(append, &newLine, 1);
   
   while((buffer = getc(fp)) != EOF){
       printf("%c", buffer);
   }
   printf("%s", &append);
   fprintf(fp, append);
    
   fclose(fp);
   return 0;
}
