#include <stdio.h>
#include <stdlib.h>

int Fibbonacci(int qty){
  if(qty == 1 || qty == 2) return 1;
  return Fibbonacci(qty - 1) + Fibbonacci(qty - 2);
}

int main(){
  int qty;
  printf("Enter how many number you want: ");
  scanf("%d", &qty);
  printf("The series are:");
  for (int i = 1; i <= qty; i++) printf("%d ", Fibbonacci(i));
  return 0;
}