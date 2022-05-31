#include <stdio.h>

int main(void) {

	setbuf(stdout, NULL);

		int i;		//first command number
		int a;		//decimal number
		int b;		//power number

		while(1){
			//get user input
			printf("Enter the command number:\n");
			printf("0) Exit\n");
			printf("1) Powers of 2\n");
			printf("2) Divisions of 2\n");

			scanf("%d", &i);

			//break if user wants to exit
			if(i == 0){
				break;
			}
			//powers of 2
			else if(i == 1){
				printf("Enter a decimal number: ");
				scanf("%d", &a);

				printf("Enter a power number: ");
				scanf("%d", &b);

				printf("%d*(2^%d) = %d \n", a, b, a << b);

			}
			//divisions by 2
			else if(i == 2){
				printf("Enter a decimal number: ");
				scanf("%d", &a);

				printf("Enter a power number: ");
				scanf("%d", &b);

				printf("%d/(2^%d) = %d \n", a, b, a >> b);
			}
			//error check
			else{
				printf("Error: number out of range.\n");
			}

			a = 0;
			b = 0;

		}

	return 0;
}
