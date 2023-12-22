#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void moveParticles(int ** records, int numSec, int row){
  // Move the particles
  for (int p = 0; p < numSec; p++) {
    for (int i = 0; i < row; i++) {
      // Check if it hits the bound
      if (records[i][0] + records[i][2] >= 20 || records[i][0] + records[i][2] <= -1) records[i][2] = 0 - records[i][2];
      if (records[i][1] + records[i][3] >= 20 || records[i][1] + records[i][3] <= -1) records[i][3] = 0 - records[i][3];
      records[i][0] += records[i][2];
      records[i][1] += records[i][3];
      // Check if particles meet and collide with already moved particles
      for (int k = 0; k < i; k++) {
        if (records[i][0] == records[k][0] && records[i][1] == records[k][1]) {
          records[i][0] = records[i][1] = records[k][0] = records[k][1] = -999;
        }
      }
    }
  }
}

int main(int argc, char *argv[]){
  // Validate arguments
  if (argc != 4) {
      printf("Usage: %s <input_file>  <output_file> <num_sec>\n", argv[0]);
      return 1;
  }

  char *inputFileName = argv[1];
  char *outputFileName = argv[2];
  int numSec = atoi(argv[3]);

  // Open the input file for reading
  FILE *input = fopen(inputFileName, "r");
  if (input == NULL) {
    printf("Failed to open the input file .\n");
    return 1;
  }

  // Read information from the input file to char array `records`.
  int **records = (int **)malloc(sizeof(int *) * 100);

  int row = 0;
  int col = 0;
  char *token = NULL;
  char *temp = (char *) malloc (100 * sizeof(char));
  while (fgets(temp, 100, input)!= 0) {
    records[row] = (int *)malloc(4 * sizeof(int));
    token = strtok(temp, ",");
    while (token != NULL && col < 4) {
      records[row][col++] = atoi(token);
      token = strtok(NULL, ",");
    }
    col = 0;
    row ++;
  }
  free(temp);
  fclose(input); 
  
  // Reallocate memory for records to its actual size
  records = (int **) realloc(records, sizeof(int *) * row);

  // Move the particles
  moveParticles(records, numSec, row);

  // Write to the output file
  FILE *output;
  output = fopen (outputFileName, "w");

  if (output == NULL) {
      perror("Error opening the output file");
      return 1;
  }

  fputs("**********************\n", output);
  
  for (int i = 0; i < 20; i++) {
    fputs("*", output);
    for (int j = 0; j < 20; j++) {
      int k = 0;
      while(k < row) {
        if (19 - records[k][1] == i && records[k][0] == j) {
          fputs("+", output);
          break;
        }
        k++;
      }
      if (k == row) fputs(" ", output);
    }
    fputs("*\n", output);
  }

  fputs("**********************", output);
  fclose(output);

  // Free memory to prevent memory leak
  for(int i = 0; i < row; i++) {
    free(records[i]);
  }
  free(records);
}