#include <stdio.h>

#define MAX 8

int to_binary(int a, int bin[]){
	int i = 0;		//index

	while(a > 0){
		bin[i] = a%2;	//see if a is divisible by 2 and add result to bin
		a = a/2;	//divide a by 2
		i++;	//index
	}
	return i;	//return number of times we divide by 2
}

void to_gray(int a, int bin[], int gray[]){
	int i = to_binary(a, bin);		//change a into binary

	for(int j = i-1; j >= 0; j--){		//go through binary number
		if(j==i-1){		//first bit in binary number
			gray[j] = bin[j];
		}
		else{
			gray[j] = bin[j+1]^bin[j];		//add XOR of i-th bit and i-th+1 bit to gray code
		}
	}
	//prints gray code
	printf("Gray Code for %d: ", a);
	for(int j = i-1; j>=0; j--){
		printf("%d", gray[j]);
	}
	printf("\n");
}

int main(void) {

	setbuf(stdout, NULL);

		int i;		//first command number
		int x;		//decimal to change into gray code
		int bin[MAX];		//binary number of 'x'
		int gray[MAX];		//gray code of 'bin'

		while(1){
			//get user input
			printf("Enter the command number:\n");
			printf("0) Exit\n");
			printf("1) Gray code\n");

			scanf("%d", &i);

			//break if user wants to exit
			if(i == 0){
				break;
			}
			//user wants a gray code
			else if(i == 1){
				//get user decimal number
				printf("Enter a decimal number: ");
				scanf("%d", &x);
				//makes sure number is in range of 0-255
				while(x < 0 || x > 255){
					printf("Error: number out of range.\n");
					printf("Enter a decimal number: ");
					scanf("%d", &x);
				}
				//changes 'x' to gray code
				to_gray(x, bin, gray);
			}
			//error check
			else{
				printf("Error: number out of range.\n");
			}

		}

	return 0;
}
