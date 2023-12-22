# include <stdio.h>
# include <stdlib.h>
# include <math.h>

int getLcm(int sm, int lg, int times) {
  if ((sm * times)%lg==0) return sm * times;
  return getLcm(sm, lg, times + 1);
}

int main() {
  int num1;
  printf("Input 1st number of LCM : ");
  scanf("%d", &num1);
  int num2;
  printf("Input 2nd number of LCM : ");
  scanf("%d", &num2);
  int sm = num1 < num2? num1: num2;
  int lg = num1 >= num2? num1: num2;
  int lcm = getLcm(sm, lg, 1);
  printf("The least common multiple of %d and %d is %d", sm, lg, lcm);
  return 0;
}