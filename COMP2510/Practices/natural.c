#include <stdio.h>
#include <stdlib.h>


void printNaturalNumbers(int count) {
  if (count > 1) printNaturalNumbers(count - 1);
  printf("%d ", count);
}

int main(){
  printf("The natural numbers are: ");
  printNaturalNumbers(50);
  return 0;
}