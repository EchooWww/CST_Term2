# include <stdio.h>
# include <stdlib.h>
# include <string.h>

void reverseString(char * str, int start, int end) {
    if (start < end) {
      char temp = *(str + start);
      *(str + start) = *(str + end);
      *(str + end) = temp;
      reverseString(str, start + 1, end - 1);
    }
}

int main(){
    char * input = (char *) malloc(100 * sizeof(char));
    printf("Input any string: ");
    scanf("%s", input);
    int size = strlen(input);
    reverseString(input, 0, size-1);
    printf("The reversed string is: %s", input);
}