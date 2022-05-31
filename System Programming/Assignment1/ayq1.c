#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char *argv[]) {
	  
	  int fd = open(argv[1], O_RDONLY);
	  
	  int size = lseek(fd, 0, SEEK_END);
	  
	  printf("Size of the file: %d\n", size);
	  
	  close(fd);
	  
	  return 0;   
}
