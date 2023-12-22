#include <stdio.h>
#include <stdlib.h>

int getMax(int * arr, int size) {
    if (size == 1) return *arr;
    return getMax(arr, size - 1) > *(arr+size - 1) ? getMax(arr, size - 1):*(arr+size - 1);
}

int main() {
  int size;
  printf("Input the number of elements to be stored in the array: ");
  scanf("%d", &size);
  int * arr = (int *) malloc(size * sizeof(int));
  for (int i = 0; i < size; i++) {
    printf("element -%d: ", i);
    scanf("%d", arr + i);
  }
  int max = getMax(arr, size);
  printf("Largest element of the array is: %d", max);
}