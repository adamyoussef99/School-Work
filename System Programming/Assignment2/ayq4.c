#include<stdio.h> 
#include<stdlib.h>
#include<string.h> 

int main(int argc, char *argv[]){  
   FILE *fp = fopen(argv[argc-1], "w");
   FILE *f;
   char c[400];
	
   for(int i = 1; i<argc-1; i++){
       f = fopen(argv[i], "r");
       while(fgets(c, 400, f)){
           fprintf(fp, "%s", c);
       }
       fclose(f);    
   }	

   fclose(fp);
   exit(0);
}

