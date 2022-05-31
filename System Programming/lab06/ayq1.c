//Adam Youssef
//104785050

#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>


int main() {
    for (int i = 1; i <= 64; i++) {
        printf("%d: %s\n", i, strsignal(i));
    }
    return 0;
}

//Using the kill -l command in the lab specifications, I found that there are 64 signals supported with strsignal().
//Thus, I made a loop from 1 to 64 to get all of the signals and printed them out. 
