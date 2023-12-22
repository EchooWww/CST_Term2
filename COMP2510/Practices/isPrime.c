# include <stdio.h>
# include <stdlib.h>
# include <math.h>

int isPrime(int num, int check) {
  if (num % check == 0) return 1;
  if (check > 3) return isPrime(num, check - 1);
  return 0;
}

int main() {
  int num;
  printf("Input any positive integer : ");
  scanf("%d", &num);
  int prime = isPrime(num, (int)sqrt((double)num));
  printf("The number %d is%s a prime number.", num, prime == 0 ? "" : " not");
  return 0;
}