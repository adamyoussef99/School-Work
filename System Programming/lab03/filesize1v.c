//filesize1v.c
#include <stdio.h>
#include<unistd.h>

int main(int argc, char *argv[]){  
	FILE *fd;
	
	int ch;  //what will happen if you uncomment this line and comment the 		//next line
        //char ch;

	int fileSize=-1;

	fd = fopen(argv[1], "r"); 

	do{
		ch=getc(fd);  //0xFF
		fileSize++;
		printf("fileSize=%d\n", fileSize);
		printf("Char read is ch=%c, in hex ch=%#hhx EOF is %#x\n\n", ch, ch, EOF);
		sleep(1);
	} while( ch != EOF);  //ch =0x FF,  EOF=0x FFFF FFFF
	
	printf(" \nout of do while loop now.\n\n");
	printf("ch=%d EOF=%#x\n", ch, EOF);
	printf("size of char =%ld size of EOF=%ld\n", sizeof(char), sizeof(EOF));
	
	printf("Size of %s is %d\n", argv[1], fileSize);
}


//suggeson: levae all the printf statment in place
//run the code to observe the output
//then use gdb to find out the "bug"

