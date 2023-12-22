#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char *ANum = "AXXXXXXXX";

void rotate_matrix(int** matrix, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            int temp = *(*(matrix + i) + j);
            *(*(matrix + i) + j) = *(*(matrix + j) + i);
            *(*(matrix + j) + i) = temp;
        }
    }
    for (int i = 0; i < n; i++) {
        for(int j = 0; j < n/2; j++) {
            int temp = *(*(matrix + i) + j);
            *(*(matrix + i) + j)= *(*(matrix + i) + n - 1 - j);
             *(*(matrix + i) + n - 1 - j)= temp;
        }
    }
    
}

int main() {
    FILE *file = fopen("input.txt", "r");
    if (file == NULL) {
        printf("Failed to open the input file.\n");
        return 1;
    }

    int n;
    fscanf(file, "%d", &n);  // assuming the first line of the file contains the matrix size

    int** matrix = malloc(n * sizeof(int*));
    for (int i = 0; i < n; ++i) {
        *(matrix + i) = malloc(n * sizeof(int));
        for (int j = 0; j < n; ++j) {
	    fscanf(file, "%1d", (*(matrix + i) + j));
        }
    }
    fclose(file);

    rotate_matrix(matrix, n);

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
	  printf("%d", *(*(matrix + i) + j));
        }
        printf("\n");
    }

    for (int i = 0; i < n; ++i) {
      free(*(matrix + i));
    }
    free(matrix);

    // Create an output file with the name stored in ANum
    FILE *outputFile = fopen(ANum, "w");

    // Check if the output file was created successfully
    if (outputFile == NULL) {
        printf("Failed to create the output file.\n");
        return 1;
    }

    fclose(outputFile);

    return 0;
}
