#include<stdio.h>

int main(int argc, char *argv[]){
   FILE *fp = fopen(argv[1], "r");
   
   FILE *fw = fopen(argv[2], "w");
   
   int current=0;
   int count = 1;
   int n=1;
   int pos[150];
   char buffer[150];
   
   while(!feof(fp)){
       fgets(buffer,150,fp);
       
       if(feof(fp)){
           break;
       }
       
       n=count;
       pos[n]=ftell(fp);
       count=count+1;
   }
   
   for(int i = count-2; i >= 0; i--){
       fseek(fp,pos[i],SEEK_SET);
       fgets(buffer,150,fp);
       fputs(buffer,fw);
   }

   fclose(fp);
   fclose(fw);
   return 0;
}
