#include <stdio.h>
#include <stdlib.h>

int sumFrom1(int upper) {
  if (upper == 1) return 1;
  return sumFrom1(upper - 1) + upper;
}

int main(){
  int upperBound;
  printf("Enter the upper bound: ");
  scanf("%d", &upperBound);
  printf("The sum of numbers from 1 to %d: %d", upperBound,sumFrom1(upperBound));
  return 0;
}