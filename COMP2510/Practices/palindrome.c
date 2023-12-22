# include <stdio.h>
# include <stdlib.h>
# include <string.h>

int palindrome(char* str, int start, int end) {
  if (start <= end) {
    if (str[start] != str[end]) return 1;
    return palindrome(str, start + 1, end - 1);
  }
  return 0;
}

int main() {
  char* str = (char *) malloc(100 * sizeof(char));
  printf("Input a word to check for palindrome :");
  scanf("%s", str);
  int size = strlen(str);
  printf("The entered word is %sa palindrome\n", palindrome(str, 0, size - 1) ? "not " : "");
}