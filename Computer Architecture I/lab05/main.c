#include <stdio.h>
#include "arithmetic.h"
#include "conversion.h"

#define MAX 8

int main(void) {

	setbuf(stdout, NULL);

		int i;
		int x[MAX];
		int y[MAX];
		int z[MAX];

		while(1){

			printf("Enter the command number:\n");
			printf("0) Exit\n");
			printf("1) Addition in signed-magnitude\n");
			printf("2) Subtraction in signed-magnitude\n");

			scanf("%d", &i);

			if(i==0){
				break;
			}

			if(i < 0 || i > 3){
				printf("Error: number out of range.\n");
			}
			else{

				printf("Enter the first binary number:\n");
				for(int k = 0; k < MAX; k++){
					printf("x%d:", k);
					scanf("%d", &x[k]);
				}
				printf("Enter the second binary number:\n");
				for(int k=0; k < MAX; k++){
					printf("y%d:", k);
					scanf("%d", &y[k]);
				}

				if(i == 1){
					func_signed_mag_addition(x, y, z);
				}
				else if(i==2){
					func_signed_mag_subtraction(x, y, z);
				}
			}

			printf("Enter the output base:\n");
			printf("1) Binary\n");
			printf("2) Octal\n");
			printf("3) Decimal\n");
			printf("4) Hexadecimal\n");

			scanf("%d", &i);

			if(i==1){
				for(int j=0; j<8; j++){
					printf("%d", z[j]);
				}
				printf("\n");
			}
			else if(i==2){
				to_octal(z);
			}
			else if(i==3){
				to_decimal(z);
			}
			else if(i==4){
				to_hexadecimal(z);
			}

		}

	return 0;
}
