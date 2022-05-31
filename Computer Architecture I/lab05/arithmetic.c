#include "conversion.h"
#include <stdio.h>
#define MAX 8

void func_signed_mag_addition(int a[], int b[], int result[]){

    int current = 0;
    int remainder = 0;

    for(int i=8;i>0;i--){
    	current=a[i-1]+b[i-1];

        if(current==0){
            if(remainder>0){
                result[i-1]=1;
                remainder--;
            }
            else{
                result[i-1]=current;
            }
        }
        else if(current==1){
            if(remainder>0){
                result[i-1]=0;
            }
            else{
                result[i-1]=current;
            }
        }
        else if(current==2){
            if(remainder>0){
                result[i-1]=1;
            }
            else{
            	remainder++;
                result[i-1]=0;
            }
        }
    }

    if(remainder>0){
        printf("Overflow has occurred.\n");
    }

}


void func_signed_mag_subtraction(int a[], int b[], int result[]){

	int current = 0;
	int remainder = 0;

    for(int i = 0; i < 8 ;i++){
        current=a[i]-b[i];

		if(current==0){
			if(remainder>0){
				result[i]=1;
				remainder--;
			}
			else{
				result[i]=current;
			}
		}
		else if(current==1){
			if(remainder>0){
				result[i]=0;
			}
			else{
				result[i]=current;
			}
		}
		else if(current==-1){
			if(remainder>0){
				result[i]=0;
			}
			else{
				remainder++;
				result[i]=1;
			}
		}
	}

	if(remainder>0){
		printf("Overflow has occurred.\n");
	}

}
