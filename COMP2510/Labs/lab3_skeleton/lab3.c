#include <stdio.h>
#include <stdlib.h>

// Function to rotate a 2D array by a given angle (in degrees)
void rotateArray(char **arr, int n, int rows, int cols) {
    // get the necessary degree to rotate
    int degree;
    if (n < 0) degree = 360 + n % (360);
    else degree = n % 360;

    for(int k = degree / 90; k >0; k--) {
        // rotate the array 90 degree clockwise
        for (int i = 0; i < rows; i++) {
            for (int j = i+1; j < cols; j++) {
                char temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols/2 ; j++) {
                char temp = arr[i][j];
                arr[i][j] = arr[i][cols - 1 - j];
                arr[i][cols - 1 - j] = temp;
            }
        }
    }
}

// Function to zoom (expand or shrink) a 2D array by a given factor
void zoomArray(char **arr, double zoomFactor, int *rows, int *cols) {

    // update rows and cols after zoom
    *rows = (int)(*rows * zoomFactor);
    *cols = (int)(*cols * zoomFactor);

    // update the actual array after zoom
    if (zoomFactor < 1) {
        int times = (int)(1/zoomFactor);
        for (int i = 0; i < *rows; i++) {
            for (int j = 0; j < *cols; j++) {
                arr[i][j] = arr[i*times][j*times];
            }
        }
    } else {
        int times = (int)zoomFactor;
        for (int i = *rows - 1; i >= 0; i--) {
            for (int j = *rows - 1; j >= 0; j--) {
                arr[i][j] = arr[i / times][j / times];
            }
        }
    }
}

int main(int argc, char *argv[]) {
    if (argc != 4) {
        printf("Usage: %s <input_file> <angle_degrees> <zoom_factor>\n", argv[0]);
        return 1;
    }

    char *inputFileName = argv[1];
    int angle = atoi(argv[2]);
    double zoomFactor = atof(argv[3]); // Use atof to convert the zoom factor to a double

    // Initialize a 2D array of size 30x30 as a pointer-to-pointer
    char **arr = (char **)malloc(30 * sizeof(char *));
    for (int i = 0; i < 30; i++) {
        arr[i] = (char *)malloc(30 * sizeof(char));
    }

    // Open the input file for reading
    FILE *file = fopen(inputFileName, "r");
    if (file == NULL) {
        printf("Failed to open the input file.\n");
        return 1;
    }

    // Read the input array from the specified file and populate arr
    int rows = 0;
    int cols = 0;
    char c;

    while (rows < 30 && cols < 30 && fscanf(file, "%c", &arr[rows][cols]) == 1) {
        if (arr[rows][cols] == '\n') {
          rows++;
          cols = 0;
        } else {
          cols++;
        }
    }
    cols = rows;
    
    // Close the input file
    fclose(file);

    // Print the array
    printf("Input Array:\n");
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          printf("%c ", arr[i][j]);
        }
        printf("\n");
    }

    
    // Call rotateArray or zoomArray based on the angle and zoomFactor
    // For example:
    rotateArray(arr, angle, rows, cols);
    zoomArray(arr, zoomFactor, &rows, &cols);

    // Print the array
    printf("Output %dx%d Array:\n", rows, cols);
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          printf("%c ", arr[i][j]);
        }
        printf("\n");
    }

    // Free dynamically allocated memory when done
    for (int i = 0; i < 30; i++) {
        free(arr[i]);
    }
    free(arr);

    return 0;
}
