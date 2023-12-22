# include <stdio.h>
# include <stdlib.h>
# include <string.h>

void strcopy(char * first, char * copy, int start) {
  if (start >= strlen(first)) return;
  copy[start] = first[start];
  strcopy(first, copy, start + 1);
}

int main() {
  char * first = (char *)malloc(100 * sizeof(char));
  printf("Input the string to copy : ");
  scanf("%s", first);
  printf("The first string is: %s \n", first);

  char * copy = (char *)malloc((strlen(first) + 1) * sizeof(char));
  strcopy(first, copy, 0);
  printf("The copied string is:  %s", copy);

}