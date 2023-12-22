#include <stdio.h>
#include <stdlib.h>

void generateArray(int * arr, int qty) {
    if (qty > 0){
      arr[qty - 1] = qty * 2;
      generateArray(arr, qty - 1);
    }
}

int main(){
  int qty;
  printf("Enter how many number you want: ");
  scanf("%d", &qty);
  int * arr = malloc(qty * sizeof(int));
  generateArray(arr, qty);
  printf("The elements in the array are: ");
  for (int i = 0; i <qty; i++) printf("%d ", arr[i]);
  free(arr);
  return 0;
}