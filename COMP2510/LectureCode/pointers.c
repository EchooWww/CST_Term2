#include <stdio.h>
#include <stdlib.h> 

void foo(int *ptr) {
  printf("my address is %p\n", ptr);
  *ptr = 4;
  return;
}
int main(int argc, char **argv) {
  int x = 3;
  int *ptr = &x; // int * is the type of ptr, not dereferencing
  foo(ptr);
  printf("%d\n", x);
  return 0;
}

