#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]){  

   FILE *fp = fopen("test.txt", "r");
       
   int count = 0;
   char buffer;
    
    while((buffer = getc(fp)) != EOF){
    	if(buffer == '\n'){
    	    count++;
    	}
    }

	  
    printf("Number of lines in the file: %d\n", count); 
    
   fclose(fp);
   return(0);
}
