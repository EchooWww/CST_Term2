#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// define a node struct
typedef struct node_t{
    int keyType;
    int valueType;
    void *key;
    void *value;
    int keySize;
    int valueSize;
    struct node_t *next;
    struct node_t *prev;
} Node;

// define a list struct
typedef struct list_t{
    Node *head;
    Node *tail;
} List;

// Function prototypes
void initList(List *list);
void clearList(List *list);
int getType(char* input, int dataSize);
void printKeys(List *list);
void printValues(List *list);
void insertKVPair(List *list, void *keyData, void *valueData, int keySize, int valueSize, int keyType, int valueType);

// initialize the list with dummy head and tail
void initList(List *list){
    list->head = malloc (sizeof(Node));
    list->tail = malloc (sizeof(Node));
    list->head->next = list->tail;
    list->tail->prev = list->head;
    list->head->prev = NULL;
    list->tail->next = NULL;
}

// clear the list for restore
void clearList(List *list){
    Node *current = list->head->next;
    while (current != list->tail) {
        Node *temp = current;
        current = current->next;
        free(temp->key);
        free(temp->value);
        free(temp);
    }
    list->head->next = list->tail;
    list->tail->prev = list->head;
}

// get the type of the data based on user input of value and size
int getType(char* input, int dataSize) {
    // check if it's a string
    if (dataSize > 1) {
        char *temp = input;
        while (*temp != '\0') {
            if ((*temp < '0' || *temp > '9') && (*temp != '.')) {
            return 5;
        }
        temp++;
        }
    }
    switch (dataSize) {
        // char
        case 1:
            return 1;
        // float
        case 4:
            if (strchr(input, '.') != NULL) {
                return 3;
            // int
            } else {
                return 2;
            }
        // double
        case 8:
            return 4;
    }
    return 5;
}

// print the keys depending on the type
void printKeys(List *list){
    printf ("Printing keys:\n");
    Node *current = list->head->next;
    while (current != list->tail) {
        switch(current->keyType) {
            case 1:
                printf("%c\n", *(char *)current->key);
                break;
            case 2:
                printf("%d\n", *(int *)current->key);
                break;
            case 3:
                printf("%f\n", *(float *)current->key);
                break;
            case 4:
                printf("%lf\n", *(double *)current->key);
                break;
            case 5:
                printf("%s\n", (char *)current->key);
                break;
        }
        current = current->next;
    }
}

// print the values depending on the type
void printValues(List *list){
    printf ("Printing values:\n");
    Node *current = list->head->next;
    while (current != list->tail) {
        switch(current->valueType) {
            case 1:
                printf("%c\n", *(char *)current->value);
                break;
            case 2:
                printf("%d\n", *(int *)current->value);
                break;
            case 3:
                printf("%f\n", *(float *)current->value);
                break;
            case 4:
                printf("%lf\n", *(double *)current->value);
                break;
            case 5:
                printf("%s\n", (char *)current->value);
                break;
        }
        current = current->next;
    }
}

// insert a key value pair to the list
void insertKVPair(List *list, void *keyData, void *valueData, int keySize, int valueSize, int keyType, int valueType){
    // create a new node and copy the data
    Node *newNode = malloc (sizeof(Node));
    newNode->keyType = keyType;
    newNode->valueType = valueType;
    newNode->key = malloc (sizeof(keySize));
    newNode->value = malloc (sizeof(valueSize));
    memcpy(newNode->key, keyData, keySize);
    memcpy(newNode->value, valueData, valueSize);
    newNode->keySize = keySize;
    newNode->valueSize = valueSize;
    
    // insert the node to the list
    newNode->next = list->tail;
    newNode->prev = list->tail->prev;
    list->tail->prev->next = newNode;
    list->tail->prev = newNode;
}

int main() {
    List *list = malloc (sizeof(List));
    initList(list);
    // prompt for user input
    char *input = malloc (sizeof(char) * 100);
    do {
        printf("1) Print Keys\n");
        printf("2) Print Values\n");
        printf("3) Insert KV Pair\n");
        printf("4) Save\n");
        printf("5) Restore\n");
        printf("6) Exit\n");
        scanf("%s", input);
        
        if (strcmp(input, "1") == 0) {
            printKeys(list);
        } else if (strcmp(input, "2") == 0) {
            printValues(list);
        } else if (strcmp(input, "3") == 0) {
        // prompt for key and value, and size of each
        printf("Insert key: ");
        char *key = malloc (sizeof(char) * 100);
        scanf("%s", key);
        printf("Key data size: ");
        int keySize;
        scanf("%d", &keySize);
        printf("Insert value: ");
        char *value = malloc (sizeof(char) * 100);
        scanf("%s", value);
        printf("Value data size: ");
        int valueSize;
        scanf("%d", &valueSize);

        // get the type of the key and value
        int keyType = getType(key, keySize);
        int valueType = getType(value, valueSize);
        void *keyData = malloc (sizeof(keySize));
        void *valueData = malloc (sizeof(valueSize));

        // copy the data to the key pointer. Pointer casting is used to copy the data as different types
        switch (keyType) {
            case 1:
                *(char *)keyData = key[0];
                break;
            case 2:
                *(int *)keyData = atoi(key);
                break;
            case 3:
                *(float *)keyData = atof(key);
                break;
            case 4:
                *(double *)keyData = atof(key);
                break;
            case 5:
                keyData = key;
        }

        // copy the data to the value pointer. Pointer casting is used to copy the data as different types
        switch (valueType) {
            case 1:
                *(char *)valueData = value[0];
                break;
            case 2:
                *(int *)valueData = atoi(value);
                break;
            case 3:
                *(float *)valueData = atof(value);
                break;
            case 4:
                *(double *)valueData = atof(value);
                break;
            case 5:
                valueData = value;
        }

        // insert the key value pair to the list
        insertKVPair(list, keyData, valueData, keySize, valueSize, keyType, valueType);

        } else if (strcmp(input, "4") == 0) {
        // prompt for file name
        printf("Enter file name:\n");
        char *fileName = malloc (sizeof(char) * 100);
        scanf("%s", fileName);

        // write the key value pairs to binary file
        FILE *fp = fopen(fileName, "wb");
        if (fp == NULL) {
            printf("Error opening file.\n");
            return 1;
        }

        // write the data to the file
        Node *current = list->head->next;
        while (current != list->tail) {
            fwrite(&current->keyType, sizeof(int), 1, fp);
            fwrite(&current->valueType, sizeof(int), 1, fp);
            fwrite(&current->keySize, sizeof(int), 1, fp);
            fwrite(&current->valueSize, sizeof(int), 1, fp);
            fwrite(current->key, current->keySize, 1, fp);
            fwrite(current->value, current->valueSize, 1, fp);
            current = current->next;
        }
        fclose(fp);
        } else if (strcmp(input, "5") == 0) {
        // prompt for file name
        printf("Enter file name:\n");
        char *fileName = malloc (sizeof(char) * 100);
        scanf("%s", fileName);

        // read the file as binary
        FILE *fp = fopen(fileName, "rb");
        if (fp == NULL) {
            printf("Error opening file.\n");
            return 1;
        }

        // clear the list for restore
        clearList(list);
        int keySize;
        int valueSize;
        int keyType;
        int valueType;

        // read the data from the file until the end
        while (fread(&keyType, sizeof(int), 1, fp) == 1) {
            fread(&valueType, sizeof(int), 1, fp);
            fread(&keySize, sizeof(int), 1, fp);
            fread(&valueSize, sizeof(int), 1, fp);
            void *keyData = malloc (sizeof(keySize));
            void *valueData = malloc (sizeof(valueSize));
            fread(keyData, keySize, 1, fp);
            fread(valueData, valueSize, 1, fp);
            
            // insert the key value pair to the list
            insertKVPair(list, keyData, valueData, keySize, valueSize, keyType, valueType);
        }
            fclose(fp);
        } else if (strcmp(input, "6") == 0) {
            printf("Bye!\n");
        } else {
            printf("Invalid input. Please try again.\n");
        }
    // exit the program if user input is 6
    } while (strcmp(input, "6") != 0);
}