# include <stdio.h>
# include <stdlib.h>
# include <string.h>

int hailstone(int start) {
  if(start == 1) {
    printf("%d ", start);
    return 1;
  }
  printf("%d ", start);
  return start % 2 == 0? hailstone(start / 2) + 1: hailstone (start * 3 + 1) + 1;

}

int main() {
  int start;
  printf("Input any positive integer to start for Hailstone Sequence: ");
  scanf("%d", &start);

  printf("The hailstone sequence starting at %d is:\n", start);
  int length = hailstone(start);
  printf("\nThe length of the sequence is %d", length);

}