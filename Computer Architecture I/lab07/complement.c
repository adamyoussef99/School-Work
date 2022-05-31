#define MAX 8
#include "arithmetic.h"

void func_1s_comp(int a[], int result[]){
	for(int i=0; i < MAX; i = i + 1){
		result[i] = a[i]^1;
	}
}

void func_2s_comp(int a[],int m){
	for(int i = 0; i < 8; i++){
			a[i] = (a[i]^m)+m;
		}
}

