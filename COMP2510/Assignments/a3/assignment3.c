#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int CURRENT = 100;
int MORE_SPACE = 100;

typedef struct node {
    struct node* left;
    struct node* right;
    char *fName;
    char *lName;
} node;

// Compare the current node with the node to be inserted. Return -1 if the current node is less than the node to be inserted, 1 if the current node is greater than the node to be inserted, and 0 if the nodes are equal
int compareNodes(char* fName, char* lName, node* current){
    if (current == NULL) {
        return -1;
    }

    int compareFirst = strcmp(fName, current->fName);  
    int compareLast = strcmp(lName, current->lName);  

    if (compareFirst < 0) {
        return -1;
    }
    if (compareFirst > 0) {
        return 1;
    }
    if (compareLast < 0) {
        return -1;
    }
    if (compareLast > 0) {
        return 1;
    }

    return 0;
}

// Search for a node in the tree and return the node if found, otherwise return NULL
int searchNode(char *fName, char *lName, node *nodes) {
    if (nodes == NULL) {
        return 0;
    }
    if (compareNodes(fName, lName, nodes) == 0) {
        return 1;
    }
    if (compareNodes(fName, lName, nodes) < 0) {
        return searchNode(fName, lName, nodes->left);
    }
    if (compareNodes(fName, lName, nodes) > 0) {
        return searchNode(fName, lName, nodes->right);
    }
    return 0;
}

// Insert a node into the tree
void insertNode(char *fName, char *lName, node **root) {
    if (*root == NULL) {
        *root = (node *) malloc(sizeof(node));
        (*root)->fName = strdup(fName);
        (*root)->lName = strdup(lName);
        (*root)->left = NULL;
        (*root)->right = NULL;
    } else {
        // Allow duplicates: if the 2 nodes are equal, insert into left subtree
        if (compareNodes(fName, lName, *root) <= 0) {
            insertNode(fName, lName, &((*root)->left));
        } else {
            insertNode(fName, lName, &((*root)->right));
        }
    }
}

// Delete node(s) from the tree if they exist, otherwise add them
void deleteNode(char *fName, char *lName, node **root) {
    // if found, delete the node
    if (searchNode(fName, lName, *root)) {
        if (compareNodes(fName, lName, *root) == 0) {
            // if the node has 0 or 1 child
            if ((*root)->left == NULL) {
                node* temp = (*root)->right;
                free(*root);
                *root = temp;
            } else if ((*root)->right == NULL) {
                node* temp = (*root)->left;
                free(*root);
                *root = temp;
            } else {
                // if the node has 2 children: find the smallest node in the right subtree
                node* temp = (*root)->right;
                while (temp->left != NULL) {
                    temp = temp->left;
                }
                (*root)->fName = temp->fName;
                (*root)->lName = temp->lName;
                deleteNode(temp->fName, temp->lName, &((*root)->right));
            }
        } else if (compareNodes(fName, lName, *root) < 0) {
            deleteNode(fName, lName, &((*root)->left));
        } else {
            deleteNode(fName, lName, &((*root)->right));
        }
    } else {
        // insert the node
        insertNode(fName, lName, root);
    }
}


// Inorder traversal of the tree and print to output file
void traverseTree(node *nodes, FILE *output) {
    if (nodes == NULL) {
        return;
    }
    traverseTree(nodes->left, output);
    fprintf(output, "%s %s\n", nodes->fName, nodes->lName);
    traverseTree(nodes->right, output);
}

// Main function to read input file and call functions
int main(int argc, char** argv) {
    
    char *ANum = "AXXXXXXXX_AXXXXXXXX_AXXXXXXXX";
    FILE *outputFile = fopen(ANum, "w");
    if (outputFile == NULL) {
        printf("Failed to create the output file.\n");
        return 1;
    }
    fclose(outputFile);

    if (argc != 3) {
        printf("Usage: %s <input file> <output file>\n", argv[0]);
        return 1;
    }

    FILE* input = fopen(argv[1], "r");
    if (input == NULL) {
        printf("Error: cannot open file '%s'\n", argv[1]);
        return 1;
    }

    node* root = NULL;

    FILE* output = fopen(argv[2], "w");
    if (output == NULL) {
        printf("Error: cannot open file '%s'\n", argv[2]);
        return 1;
    }

    char *buffer = malloc(CURRENT);
    int option;

    int ch;
    size_t bufferSize = CURRENT;
    size_t currentIndex = 0;
    size_t fNameSize = 0;
    size_t lNameSize = 0;

    while (1) {
        ch = fgetc(input);
        // Check if the buffer is full, realloc if necessary
        if (currentIndex == bufferSize - 1) {
            MORE_SPACE = MORE_SPACE * 2;
            bufferSize += MORE_SPACE;
            buffer = realloc(buffer, bufferSize);
            if (buffer == NULL) {
                fprintf(stderr, "Memory reallocation error\n");
                return 1;
            }
        }

        // Store the character in the buffer
        buffer[currentIndex++] = (char)ch;

        // Check if the line is complete
        if (ch == '\n' || ch == EOF) {
            buffer[currentIndex] = '\0';  // Null-terminate the string
            int option;
            // Allocate memory for fName and lName based on fNameSize and lNameSize
            char *fName = malloc(fNameSize + 1);
            char *lName = malloc(lNameSize + 1);

            if (fName == NULL || lName == NULL) {
                fprintf(stderr, "Memory allocation error\n");
                return 1;
            }
            // Process the line as needed
            if (sscanf(buffer, "%s %s %d", fName, lName, &option) == 3) {
                switch (option) {
                    case 1:
                        insertNode(fName, lName, &root);
                        break;
                    case 2:
                        deleteNode(fName, lName, &root);
                        break;
                    case 3:
                        if (searchNode(fName, lName, root)) {
                            fprintf(output, "Found\n");
                        } else {
                            fprintf(output, "Not Found\n");
                        }
                        break;
                    default:
                        fprintf(output, "Error: invalid input ");
                        free(fName);
                        free(lName);
                        free(buffer);
                        return 1;
                }
            } else if (sscanf(buffer, "%d", &option) == 1) {
                if (option == 4) {
                    traverseTree(root, output);
                } else {
                    fprintf(output, "Error: invalid input");
                    free(fName);
                    free(lName);
                    free(buffer);
                    return 1;
                }
            } else if (ch != EOF)  {
                fprintf(output, "Error: invalid input");
                free(fName);
                free(lName);
                free(buffer);
                return 1;
            }
            if (ch == EOF) {
                break;
            }


            // Free dynamically allocated memory for fName and lName
            free(fName);
            free(lName);
            // Reset the buffer to all null characters
            memset(buffer, 0, bufferSize);

            // Reset for the next line
            currentIndex = 0;
            fNameSize = 0;
            lNameSize = 0;
        } else if (ch == ' ' && fNameSize == 0) {
            fNameSize = currentIndex - 1;
        } else if (fNameSize > 0 && ch == ' ') {
            lNameSize = currentIndex - fNameSize - 2;
        }
    }

    // Free dynamically allocated memory
    free(buffer);
    fclose(input);
    return 0;
}
