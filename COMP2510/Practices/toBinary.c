# include <stdio.h>
# include <stdlib.h>
# include <math.h>

void toBinary (int dec, char * bin, int size) {
  if (dec > 0) {
    bin[size-1] =  dec % 2 + '0';
    toBinary(dec / 2, bin, size - 1);
  }
}

int main() {
  int dec;
  printf("Input any decimal number : ");
  scanf("%d", &dec);
  int size = (int)(log((double)dec) / log(2.0)) + 1;
  char *bin = (char *) malloc((size + 1) * sizeof(char));
  toBinary(dec, bin, size);
  printf("The Binary value of decimal no. %d is %s", dec, bin);
  free(bin);
  return 0;
}