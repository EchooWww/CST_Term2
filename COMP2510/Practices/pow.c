# include <stdio.h>
# include <stdlib.h>
# include <string.h>

int calcPower(int base, int power) {
  if (power == 0) return 1;
  return base * calcPower (base, power - 1);
}

int main() {
  int base, power;
  printf("Input the base value: ");
  scanf("%d", &base);
  printf("Input the value of power: ");
  scanf("%d", &power);
  
  printf("The value of %d to the power of %d is %d", base, power, calcPower(base, power));
}