#include <stdio.h>
#define MAX 8//Byte = 8 bits

void func_and(int a[], int b[], int result[]){
	for(int i=0; i < MAX; i = i + 1){
		result[i] = a[i] & b[i];
	}
}

void func_or(int a[], int b[], int result[]){
	for(int i=0; i < MAX; i = i + 1){
		result[i] = a[i] | b[i];
	}
}

void func_not(int a[], int result[]){
	for(int i=0; i < MAX; i = i + 1){
		result[i] = !a[i];
	}
}

void func_1s_comp(int a[], int result[]){
	func_not(a, result);
}

void func_2s_comp(int a[], int result[]){
	func_1s_comp(a, result);
	for(int i=0; i < MAX; i = i + 1){
		if(result[i] == 0){
			result[i] = 1;
			break;
		}
		else{
			result[i] = 0;
		}
	}
}

void func_2s_comp_star(int a[], int result[]){
	func_1s_comp(a, result);
	int checker = 0;
	for(int i=MAX-1; i >= 0; i = i - 1){
		if(result[i] == 1 && checker == 0){
			checker = 1;
			i = i - 1;
		}
		if(checker == 1){
			result[i] = !result[i];
		}
	}
}

int main(void) {
	setbuf(stdout, NULL);

	int i;

	while(1){

		printf("Enter the command number:\n");
		printf("0) Exit\n");
		printf("1) AND\n");
		printf("2) OR\n");
		printf("3) NOT\n");
		printf("4) 1's compliment\n");
		printf("5) 2's compliment\n");
		printf("6) 2's compliment*\n");

		scanf("%d", &i);

		if(i==0){
			break;
		}

		if(i < 0 || i > 6){
			printf("Error: number out of range.\n");
		}

		if(i > 0 && i < 3){

			int x[MAX];
			int y[MAX];
			printf("Enter the first binary number:\n");
			for(int i=0; i < MAX; i = i + 1){
				scanf("%d", &x[i]);
			}
			printf("Enter the second binary number:\n");
			for(int i=0; i < MAX; i = i + 1){
				scanf("%d", &y[i]);
			}

			if(i == 1){
				int z[MAX];
				func_and(x, y, z);
				printf("The first number AND second binary yield:\n");
				for(int i=0; i < MAX; i = i + 1){
					printf("%d\n", z[i]);
				}
			}

			if(i == 2){
				int z[MAX];
				func_or(x, y, z);
				printf("The first number OR second binary yield:\n");
				for(int i=0; i < MAX; i = i + 1){
					printf("%d\n", z[i]);
				}
			}
		}

		if(i > 2 && i < 7){
			int x[MAX];
			printf("Enter the binary number:\n");
			for(int i=0; i < MAX; i = i + 1){
				scanf("%d", &x[i]);
			}

			if(i == 3){
				int z[MAX];
				func_not(x, z);
				printf("The number's NOT binary yield:\n");
				for(int i=0; i < MAX; i = i + 1){
					printf("%d\n", z[i]);
				}
			}
			if(i == 4){
				int z[MAX];
				func_1s_comp(x, z);
				printf("The number's 1's compliment:\n");
				for(int i=0; i < MAX; i = i + 1){
					printf("%d\n", z[i]);
				}
			}
			if(i == 5){
				int z[MAX];
				func_2s_comp(x, z);
				printf("The number's 2's compliment:\n");
				for(int i=0; i < MAX; i = i + 1){
					printf("%d\n", z[i]);
				}
			}
			if(i == 6){
				int z[MAX];
				func_2s_comp_star(x, z);
				printf("The number's 2's * compliment:\n");
				for(int i=0; i < MAX; i = i + 1){
					printf("%d\n", z[i]);
				}
			}
		}
	}

	return 0;
}
