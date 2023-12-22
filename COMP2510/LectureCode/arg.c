#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
	
	int n, m;
	n = atoi(argv[1]);
	m = atoi(argv[2]);
	printf("Argument 1: %d \nArgument 2: %d \n", n, m);
	printf("What is this? %d", argc);
	return 0;
}