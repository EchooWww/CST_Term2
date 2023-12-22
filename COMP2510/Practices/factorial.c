#include <stdio.h>
#include <stdlib.h>

int factorial(int num) {
  if (num == 1) return 1;
  return factorial(num-1) * num;
}

int main() {
  int num;
  printf("Input a positive integer:");
  scanf("%d", &num);
  int res = factorial(num);
  printf("The factorial of %d is %d", num, res);
}