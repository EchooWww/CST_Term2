
#include <stdio.h>
#include <stdlib.h>

int countDigits(int num){
  if (num < 10) return 1;
  return countDigits(num / 10) + 1;
}

int main(int argc, char** argv){
  int num = atoi(argv[1]);
  int digits = countDigits(num);
  printf("The number of digits in the number is : %d", digits);
  return 0;
}