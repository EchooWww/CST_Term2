#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char fName[20];
    char lName[20];
    char stuNo[10];
    int mtGrade;
    int finalGrade;
    float avgGrade;
} student;

int compareStudents(student* s1, student* s2) {
    int lNameCmp = strcmp(s1->lName, s2->lName);
    if (lNameCmp != 0) {
        return lNameCmp;
    }
    int fNameCmp = strcmp(s1->fName, s2->fName);
    if (fNameCmp != 0) {
        return fNameCmp;
    }
    int stuNoCmp = strcmp(s1->stuNo, s2->stuNo);
    if (stuNoCmp != 0) {
        return stuNoCmp;
    }
    int mtGradeCmp = s1->mtGrade - s2->mtGrade;
    if (mtGradeCmp != 0) {
        return mtGradeCmp;
    }
    int finalGradeCmp = s1->finalGrade - s2->finalGrade;
    if (finalGradeCmp != 0) {
        return finalGradeCmp;
    }
    return 0;
}

void quickSort(student students[], int lo, int hi) {
    if (lo >= hi) return;
    int i = lo, j = hi;
    student pivot = students[(lo + hi) / 2];
    while (i <= j) {
        while (compareStudents(&students[i], &pivot) < 0) {
            i++;
        }
        while (compareStudents(&students[j], &pivot) > 0) {
            j--;
        }
        if (i <= j) {
            student temp = students[i];
            students[i] = students[j];
            students[j] = temp;
            i++;
            j--;
        }
    }
    quickSort(students, lo, j);
    quickSort(students, i, hi);
}

int countLines (const char *filename) {
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        return -1; // Error opening file
    }
    int count = 0;
    char ch;
    while ((ch = fgetc(file)) != EOF) {
        if (ch == '\n') {
            count++;
        }
    }
    count++;
    fclose(file);
    return count;
}

int main(int argc, char *argv[]) {
    if (argc != 4) {
        printf("Usage: ./<executable> <input_file> <output_file> <option>\n");
        return 1;
    }
    int numLines = countLines(argv[1]);

    FILE *input = fopen(argv[1], "r");
    if (input == NULL) {
        printf("Cannot open file %s\n", argv[1]);
        return 1;
    }

    FILE *output = fopen(argv[2], "w");
    if (output == NULL) {
        printf("Cannot open file %s\n", argv[2]);
        return 1;
    }
    int option = atoi(argv[3]);
    if (option < 1 || option > 5) {
        printf("Option must be between 1 and 5\n");
        return 1;
    }
    char line[256];
    student *students = (student*)malloc(numLines * sizeof(student));
    int numStudents = 0;
    while (fgets(line, sizeof(line), input)!= NULL) {
        sscanf(line, "%s %s %s %d %d", 
        students[numStudents].fName, 
        students[numStudents].lName, 
        students[numStudents].stuNo, 
        &students[numStudents].mtGrade, 
        &students[numStudents].finalGrade);
        students[numStudents].avgGrade = (students[numStudents].mtGrade + students[numStudents].finalGrade) / 2.0;
        numStudents++;
    }
    quickSort(students, 0, numStudents - 1);
    for (int i = 0; i < numStudents;i++) {
        if((students[i].avgGrade > 90 && option == 1)
            || (students[i].avgGrade > 80 && students[i].avgGrade <= 90 && option == 2)
            || (students[i].avgGrade > 70 && students[i].avgGrade <= 80 && option == 3)
            || (students[i].avgGrade > 60 && students[i].avgGrade <= 70 && option == 4)
            || (students[i].avgGrade <= 60 && option == 5)) {
            fprintf(output, "%s %s %s %d %d\n", 
                    students[i].fName, 
                    students[i].lName, 
                    students[i].stuNo, 
                    students[i].mtGrade, 
                    students[i].finalGrade);
        }
    }
    free(students);
    fclose(input);
    fclose(output);
} 