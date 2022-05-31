#include<stdio.h> 
#include <stdlib.h>
#include<signal.h> 
#include <unistd.h>

// handler for SIGINT
void handler(int sig) { 
	printf("This is the handler with signal no: %d \n", sig);
	//if you hit ctrl c, it exits the program
	if(sig == 2){
		exit(0);
	} 
} 

int main() { 

	//when user press ctrl + c or ctrl + \, run the handler function
	signal(SIGINT, handler); 
	signal(SIGQUIT, handler);
	
	while(1); 
	
}
