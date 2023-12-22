# include <stdio.h>
# include <stdlib.h>

int gcd(int n1, int n2){
  if (n1 % n2 == 0) return n2;
  return gcd(n2, n1 %n2);
}

int main() {
  int num1, num2;
  printf("Input 1st number: \n");
  scanf("%d", &num1);
  printf("Input 2nd number: \n");
  scanf("%d", &num2);
  int n1 = num1 > num2? num1:num2;
  int n2 = num1 <= num2? num1:num2;
  printf("The GCD of %d and %d is %d", n1, n2, gcd(n1, n2));
  return 0;
}