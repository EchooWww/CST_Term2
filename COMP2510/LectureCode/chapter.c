#include <stdio.h>
#include <stdlib.h> 

int main() {
  char c[2];
  c[0]='h';
  c[1] = 'h';

  char **ptr = &c;
  printf("%d\n", ptr);

  return 0; 
}
