#include <stdio.h>
#include <stdlib.h>

// void evenOddSum(int lower, int higher, int * even, int * odd, int start) {
//   if (lower <= higher) {
//     even[start] = lower % 2 == 0? lower: lower + 1;
//     odd[start] = lower % 2 == 1? lower :lower + 1;
//     evenOddSum(lower + 2, higher, even, odd, start + 1);
//   }
// }

void evenOddPrint(int start, int end) {
  if (start <= end) {
    printf("%d ", start);
    evenOddPrint(start + 2, end);
  }
}

int main() {
  // int lower, higher;
  // printf("Input the lower bound of your range:");
  // scanf("%d", &lower);
  int higher;
  printf("Input the higher bound of your range:");
  scanf("%d", &higher);
  // size_t sizeEven = lower % 2 ==1 && higher %2 ==1? (higher - lower) / 2 : (higher - lower) / 2 + 1;
  // size_t sizeOdd = lower % 2 ==0 && higher %2 ==0? (higher - lower) / 2 : (higher - lower) / 2 + 1;
  // int *arrEven = (int *)malloc(sizeEven * sizeof(int));
  // int *arrOdd = (int *)malloc(sizeOdd * sizeof(int));
  // evenOddSum(lower, higher, arrEven, arrOdd, 0);
  printf("All even numbers from 1 to %d are: ", higher);
  evenOddPrint(2, higher);
  printf("\nAll odd numbers from 1 to %d are: ", higher);
  evenOddPrint(1, higher);
}