#include "arithmetic.h"
#include <limits.h>
#include <stdio.h>
#define MAX 8

void to_octal(int a[]){

	int exp = 1;
	int result = 0;

	for(int i=MAX;i>0;i--){
		result+=exp*a[i];
		exp*=2;
	}

	printf("%o\n",result);
}

void to_decimal(int a[]){
  int exp = 1;
  int result = 0;

  for(int i=MAX;i>0;i--){
	  result+=exp*a[i];
    exp*=2;
  }

  printf("%d\n",result);
}

void to_hexadecimal(int a[]){
  int exp = 1;
  int result = 0;

  for(int i=MAX;i>0;i--){
	  result+=exp*a[i];
	  exp*=2;
  }

  printf("%x\n",result);
}
