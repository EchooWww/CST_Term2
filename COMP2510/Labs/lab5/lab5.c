#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define CHAR_COUNT 256
#define LINE_COUNT 100

char * reverseWords(char * line, int start, int end) {
    char * reversedLine = malloc(sizeof(char) * (end + 2));
    // Base case: start == end or they cross each other
    while (end > 0) {
        int wordlen = (strchr(&line[start], ' ')) - &line[start];
        memcpy(&reversedLine[end + 1 - wordlen], &line[start], wordlen);
        // Recursively call the function to swap another 2 chracters
        start += wordlen + 1;
        end -= wordlen;
    }
    return reversedLine;
}

void reverseLines(char **lines, int start, int end) {
    // Base case: start == end or they cross each other
    if (start < end) {
        // Swap the lines at start and end indices
        char* temp = lines[start];
        lines[start] = lines[end];
        lines[end] = temp;
        // Recursively call the function to swap another 2 lines
        reverseLines(lines, start + 1, end - 1);
    }
}

int main(int argc, char *argv[]){
  // Validate arguments
  if (argc != 2) {
      printf("Usage: %s <input_file>\n", argv[0]);
      return 1;
  }

  char *inputFileName = argv[1];

  // Open the input file for reading
  FILE *input = fopen(inputFileName, "r");
  if (input == NULL) {
    printf("Failed to open the input file .\n");
    return 1;
  }

  // Read information from the input file to char array `lines`.
  char **lines = (char **)malloc(sizeof(char *) * LINE_COUNT);

  int row = 0;
  char *temp = (char *) malloc ((CHAR_COUNT) * sizeof(char));
  while (fgets(temp, CHAR_COUNT, input)!= 0) {
    lines[row] = (char *)malloc((CHAR_COUNT + 1) * sizeof(char));
    strcpy(lines[row++], temp);
  }
  free(temp);
  lines = realloc(lines, row * sizeof(char *));
  fclose(input); 
  
  // Reallocate memory for lines to its actual size
  lines = (char **) realloc(lines, sizeof(char *) * row);
  
  // Reverse characters in each line
  for (int i = 0; i < row; i++) {
      lines[i] = reverseWords(lines[i], 0, strlen(lines[i]) - 1);
  }
  
  // Reverse the lines
  reverseLines(lines, 0, row - 1);

  for (int i = 0; i < row; i++) {
    printf("%s", lines[i]);
  }

  // Free memory to prevent memory leak
  for (int i = 0; i < row; i++) {
    free(lines[i]);
  }
  free(lines);
}