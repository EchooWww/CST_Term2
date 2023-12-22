#include<stdio.h>
#include<stdlib.h>

int sumOfDigits(int num) {
  if (num < 10) return num;
  return sumOfDigits(num / 10) + sumOfDigits(num % 10);
}

int main(int argc, char** argv){
    int num = atoi(argv[1]);
    int sum = sumOfDigits(num);
    printf("The sum of digits of %d = %d", num, sum);
}