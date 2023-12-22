#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_INT 2147483647

// Define the Student struct
typedef struct {
    char firstName[50];
    char lastName[50];
    char gpa[10];
    char status;
    int toefl;
} InternationalStudent;

typedef struct {
    char firstName[50];
    char lastName[50];
    char gpa[10];
    char status;
} DomesticStudent;

int countLinesInFile(const char *filename) {
    FILE *file = fopen(filename, "r");
    int count = 0;
    char ch;
    while ((ch = fgetc(file)) != EOF) {
        if (ch == '\n') count++;
    }
    count++;
    fclose(file);
    return count;
}

int isNumeric(const char *str) {
    int count = 0;
    for (int i = 0; str[i] != '\0'; ++i) {
        if (str[i] == '.') count++;
        if (((str[i] < '0' || str[i] > '9') && str[i] != '.' )|| count > 1) {
            return 0;
        }
    }
    return 1;
}

int countDecimalDigits(char *str) {
    int indexPoint = strchr(str, '.') - str;
    return strlen(str) - indexPoint - 1;
}

// Check if there's any error in the record
int checkErrorI(InternationalStudent *s) {
    // Check GPA: 0.0-4.3, no more than 3 decimal digits
    if (!isNumeric(s->gpa)|| atof(s->gpa)<0.0 || countDecimalDigits(s->gpa) > 3) return 3;
    // Check status: either 'D' or 'I'
    if (s->status != 'D' && s->status != 'I') return 4;
    // Check toefl: 0-120
    if (s->toefl < 0 || s->toefl > 120) return 5;
    return 0;
}

// Check if there's any error in the record
int checkErrorD(DomesticStudent *s) {
    // Check GPA: 0.0-4.3, no more than 3 decimal digits
    if (!isNumeric(s->gpa) || atof(s->gpa)<0.0 ||  countDecimalDigits(s->gpa) > 3) return 3;
    // Check status: either 'D' or 'I'
    if (s->status != 'D' && s->status != 'I') return 4;
    return 0;
}

int handleError(int errorCode, FILE * outputFile) {
    switch(errorCode){
        case 3:
            fprintf(outputFile, "Error: Invalid GPA\n");
            break;
        case 4:
            fprintf(outputFile, "Error: Invalid status\n");
            break;
        case 5:
            fprintf(outputFile, "Error: Invalid TOEFL score\n");
            break;
    }
    return 1;
}

int main(int argc, char *argv[]) {
    // Check command line arguments
    if (argc != 4) {
        printf("Error: Incorrect number of arguments\n Usage: ./<name of executable> <input file> <output file> <option>");
        return 1;
    }

    // Parse the option argument
    int option = atoi(argv[3]);
    if (option < 1 || option > 3) {
        printf("Error: Invalid option\n");
        return 1;
    }
    
    // Count the number of lines in the input file
    int numLines = countLinesInFile(argv[1]);
    FILE *inputFile = fopen(argv[1], "r");
    if (numLines < 0 || inputFile == NULL) {
        printf("Error: Could not open input file\n");
        return 1;
    }

    // Allocate memory for the array of Student structs
    InternationalStudent *iStudents = (InternationalStudent *)malloc(numLines * sizeof(InternationalStudent));
    DomesticStudent *dStudents = (DomesticStudent *)malloc(numLines * sizeof(DomesticStudent));

    if (iStudents == NULL || dStudents == NULL) {
        printf("Error: Memory allocation failed\n");
        return 1;
    }
    
    // Open the output file
    FILE *outputFile = fopen(argv[2], "w");
    if (outputFile == NULL) {
        printf("Error: Could not open output file\n");
        free(iStudents);
        free(dStudents);
        return 1;
    }

    // Read the content of the file into an array of Student structs
    char line[256];
    char fName[50];
    char lName[50];
    char gpa [8];
    char status[255];
    int numDStudents = 0;
    int numIStudents = 0;
    int numStudents = 0;

    while(fgets(line, sizeof(line), inputFile) != NULL) {
        int toefl = MAX_INT;
        int numSpaces = 0;
        for (int i = 0; line[i] != '\0'; i++) {
            if (line[i] == ' ') numSpaces++;
        }
        if (sscanf (line, "%s %s %s %s %d", fName, lName, gpa, status, &toefl) < 4) {
            // Handle format error
            fprintf(outputFile, "Error, invalid format in line %d", numStudents + 1);
            free(iStudents);
            free(dStudents);
            return 1;
        } 
        if (strlen(status) >1) {
            fprintf(outputFile, "Error in line %d: Invalid format of students information \n", numStudents + 1);
            free(iStudents);
            free(dStudents);
            return 1;
        }

        if (status[0] == 'D') {
            if (numSpaces > 3) {
                fprintf(outputFile, "Error in line %d: Invalid format of students information \n", numStudents + 1);
                free(iStudents);
                free(dStudents);
                return 1;
            }
            if (toefl != MAX_INT) {
                fprintf(outputFile, "Error in line %d: Domestic students shouldn't have a TOEFL score\n", numStudents + 1);
                free(iStudents);
                free(dStudents);
                return 1;
            }
            strcpy(dStudents[numDStudents].firstName, fName);
            strcpy(dStudents[numDStudents].lastName, lName);
            strcpy(dStudents[numDStudents].gpa, gpa);
            dStudents[numDStudents].status = status[0];
        } else if (status[0] == 'I') {
            if (numSpaces > 4) {
                fprintf(outputFile, "Error in line %d: Invalid format of students information \n", numStudents + 1);
                free(iStudents);
                free(dStudents);
                return 1;
            }
            if (toefl == MAX_INT) {
                fprintf(outputFile, "Error in line %d: International students should have a TOEFL score\n", numStudents + 1);
                free(iStudents);
                free(dStudents);
                return 1;
            }
            strcpy(iStudents[numIStudents].firstName, fName);
            strcpy(iStudents[numIStudents].lastName, lName);
            strcpy(iStudents[numIStudents].gpa, gpa);
            iStudents[numIStudents].status = status[0];
            iStudents[numIStudents].toefl = toefl;
        }
        int errorCode;
        
        if (status[0] == 'D') {
            errorCode = checkErrorD(&dStudents[numDStudents]);
            numDStudents++;
        } else if (status[0] == 'I') {
            errorCode = checkErrorI(&iStudents[numIStudents]);
            numIStudents++;
        } else {
            fprintf(outputFile, "Error in line %d: Invalid status\n", numStudents + 1);
        }

        if (errorCode) {
            free(iStudents);
            free(dStudents);
            return handleError(errorCode, outputFile);
        }
        if ((option == 1 || option == 3) && status[0] == 'D' && atof(gpa)>3.9) {
            fprintf(outputFile, "%s %s %s %c\n", fName, lName, gpa, status[0]);
        } else if ((option == 2 || option == 3) && status[0] == 'I' && atof(gpa)>3.9 && toefl>= 70) {
            fprintf(outputFile, "%s %s %s %c %d\n", fName, lName, gpa, status[0], toefl);
        }
        numStudents++;
    }
    fclose(inputFile);
    fclose(outputFile);
    free(iStudents);
    free(dStudents);
    return 0;
}
