# include <stdio.h>

int binarySearch(int * arr, int target, int start, int end) {
  if (start > end) return 0;
  int mid = start + (end - start) / 2;
  if (arr[mid] == target) return 1;
  if (arr[mid] > target) return binarySearch(arr, target, start, mid - 1);
  return binarySearch(arr, target, mid + 1, end);
}

int main() {
  int n1, n2, n3, n4, n5, target;
  printf("Input the 1st number : "); 
  scanf("%d", &n1);
  printf("Input the 2nd number : "); 
  scanf("%d", &n2);
  printf("Input the 3th number : "); 
  scanf("%d", &n3);
  printf("Input the 4th number : "); 
  scanf("%d", &n4);
  printf("Input the 5th number : "); 
  scanf("%d", &n5);
  printf("Input the number you wanna search: "); 
  scanf("%d", &target);
  int range[5] = {n1, n2, n3, n4, n5};
  if (binarySearch(range, target, 0, 4)) {
    printf("The search number found in the array.");
  } else {
    printf("The search number NOT found in the array.");
  }
  return 0;
}