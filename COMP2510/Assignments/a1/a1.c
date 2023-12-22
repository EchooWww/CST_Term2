#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MORE_SPACE 1024


// checkLongWords: If there's any long word exceeds the lineLimit, return 1, else return 0.
int checkLongWords(char ** wordList, int wordCount, int lineLimit){
  for (int i = 0; i < wordCount; i++) {
    if (strlen(wordList[i]) > lineLimit) return 1;
  }
  return 0;
}

/*
  formatWords: format the wordList with a new array output and print it.
  char ** output: a helper array to hold the words to be printed in this line
  if the word can be added without exceeding the length: add it to the current row of output;
  if current word will make this line too long OR we're at the last word: print the line and inserted spaces without adding the word
*/ 
void formatWords(char ** wordList, int wordCount, int lineLimit){
  
  // Allocate space for output pointers
  size_t outCap = MORE_SPACE;
  char ** output = (char **) malloc(sizeof(char *) * outCap);

  /*
    currLen: the length of current line in output;
    currLine: the index of current line;
    wordLine: the current line of word in the wordList; 
    currLineWords: word count of the current line in output 
  */  
  int currLen = 0, currLine = 0, wordLine = 0, currLineWords = 0;
  
  // Loop until all the words has been formatted
  while (wordLine <= wordCount) {
    
    // Allocate space for each line
    if (currLen == 0) {
      output[currLine] = (char *)malloc(sizeof(char) * (lineLimit+1));
      currLineWords = 0;
      // Copy the first word to the line in our output array
      strcpy(&output[currLine][currLen], wordList[wordLine]);
      currLen += strlen(wordList[wordLine]);
      wordLine++;
      currLineWords++;
    }
    
    // If the current word cannot be added to the current line OR we reached the last word in wordList: print the current line
    if (wordLine == wordCount 
    || (output[currLine][currLen-1] != '-' && currLen + 1 + strlen(wordList[wordLine]) > lineLimit) 
    || (output[currLine][currLen-1] == '-' && currLen + strlen(wordList[wordLine]) > lineLimit)) {
      // If there's only one word to be printed in this line, print the spaces before and after it
      if (currLineWords == 1) {
        int right = (lineLimit - currLen) / 2, left = (lineLimit - currLen) % 2 == 0? right: right + 1;
        for(int i = 0; i < left; i++) printf("%c", ' ');
        printf("%s", output[currLine]);
        for(int i = 0; i < right; i++) printf("%c", ' ');
      
      // If there are multiple words in the current line, print the spaces in between
      } else {
        // Calculate the spaces to be added
        int spaceBetween = (lineLimit - currLen) / (currLineWords - 1);
        int extraSpace =  (lineLimit - currLen) % (currLineWords - 1);
        for(int i = 0; i < currLen; i++) {
          printf("%c", output[currLine][i]);
          if (output[currLine][i] == ' ') {
            for (int j = 0; j < spaceBetween; j++)  printf("%c", ' ');
            if (extraSpace-- > 0) printf("%c", ' ');
          }
        }
      }
      // Go to the next line and increment the line No.
      printf("\n");
      currLine ++;
      currLen = 0;

      // If the output line exceeds array capacity, reallocate more memory
      if (currLine == sizeof(output) / sizeof(char **)) {
        outCap += MORE_SPACE;
        output = (char **)realloc(output, sizeof(char *) * outCap);
      }

      // If we reached the last word of wordList, reallocate output to the actual size needed and break the loop
      if (wordLine == wordCount) {
        output = (char **)realloc(output, sizeof(char *) * currLine);
        break;
      }

      // If the current word can be added to the current line
    } else {
      // If current word is not the first in the current line and not the second half of a hyphenated word, add a space before the word
      if (currLineWords > 0 && output[currLine][currLen-1] != '-') {
        output[currLine][currLen++] = ' ';
      }
      // Copy the word from our wordList to the output
      if (output[currLine][currLen-1] != '-') {
        currLineWords++; // increment currLineWords if the current word isn't second half of hyphenated word added right after the hyphen
      }
      // Copy the word from wordList to output
      strcpy(&output[currLine][currLen], wordList[wordLine]);
      currLen += strlen(wordList[wordLine]);
      wordLine++;
    }
  }
  // Free memory
  for (int i = 0; i < currLine; i++) free(output[i]);
  free(output);
}


int main(int argc, char* argv[]) {
  // check the arguments
  if (argc != 3) {
      printf("Usage: %s <char_number> <input_file>\n", argv[0]);
      return 1;
  }
  int lineLimit = atoi(argv[1]);
  char *inputFileName = argv[2];
  // open the file
  FILE *fp;
  fp = fopen(inputFileName, "r");
  if (fp == NULL) {
      perror("Error opening file");
      return 1;
  } 
  
  // Read the words from file to wordList
  int row = 0;
  size_t capacity = MORE_SPACE;
  char **wordList = (char **)malloc(sizeof(char *) * capacity);
  char *temp = (char *) malloc (sizeof(char) * 500);
  
  // Scan the words and store in temp
  while(fscanf(fp, "%s", temp) == 1) {
    
    // If there's no hyphen in the current word, add it to the wordList directly
    if (strchr(temp, '-') == NULL) {
      wordList[row] = (char *) malloc(strlen(temp) + 1);
      strcpy(wordList[row++], temp);
    
    // If there is a hyphen in the current word, add the first half(including hyphen) and second half as 2 lines of words
    } else {
      int firstHalf = strchr(temp, '-') - temp + 1;
      wordList[row] = (char *) malloc(firstHalf + 1);
      memcpy(wordList[row], temp, firstHalf);
      wordList[row][firstHalf] = '\0'; // Null-terminate the first half
      row++;
      wordList[row] = (char *) malloc(strlen(temp) - firstHalf + 1);
      strcpy(wordList[row++], strchr(temp, '-') + 1);
    }
    
    // If the file content exceeds array capacity, reallocate more memory
    if (row == sizeof(wordList) / sizeof(char **)) {
      capacity += MORE_SPACE;
      wordList = (char **)realloc(wordList, sizeof(char *) * capacity);
    }
  }
  free(temp);
  fclose(fp);
  
  // Reallocate the exact space for wordList when done reading the file
  wordList = (char **)realloc(wordList, sizeof(char *) * row);
  
  // If there are words exceeds the lineLimit, print the error message and return. If not, format and print.
  if (checkLongWords(wordList, row, lineLimit) == 1 || lineLimit <= 0) {
    printf("Error. The word processor can't display the output.\n");
    return 1;
  } else {
    formatWords(wordList, row, lineLimit);
  }
  
  // Free momery to avoid memory leak
  for(int i = 0; i < row; i++) free(wordList[i]);
  free(wordList);
  
  // A number information
  char *ANum = "AXXXXXXXX_AXXXXXXXX_AXXXXXXXX";

  FILE *outputFile = fopen(ANum, "w");

  if (outputFile == NULL) {
      printf("Failed to create the output file.\n");
      return 1;
  }

  fclose(outputFile);
  return 0;
}