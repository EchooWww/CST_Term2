#include <stdio.h>
#include <stdlib.h> 

int main() {
  int a[3] = {1,2,3};
  int b[3] = {4,5,6};
  a = b;
  printf("%d\n", a[0]);
  printf("%d\n", b[1]);
}