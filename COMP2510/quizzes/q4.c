#include <stdio.h>
#include <stdlib.h>

// Definition for a binary tree node.
struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

struct TreeNode* deleteNode(struct TreeNode* root, int key) {
    // base case:
    if (root == NULL) return root;
    if (root->val < key) {
        root->right = deleteNode(root->right, key);
    } else if (root->val > key) {
        root->left = deleteNode(root->left, key);
    // we find the node to be deleted  
    } else {
        // if the node has 0 or 1 child
        if (root->left == NULL) {
            struct TreeNode* temp = root->right;
            free(root);
            return temp;
        } else if (root->right == NULL) {
            struct TreeNode* temp = root->left;
            free(root);
            return temp;
        }
        // if the node has 2 children: find the smallest node in the right subtree
        struct TreeNode* temp = root->right;
        while (temp->left != NULL) {
            temp = temp->left;
        }
        root->val = temp->val;
        root->right = deleteNode(root->right, temp->val);
    }
    return root;
}

// Helper function to create a new TreeNode
struct TreeNode* newNode(int val) {
    struct TreeNode* node = (struct TreeNode*)malloc(sizeof(struct TreeNode));
    node->val = val;
    node->left = NULL;
    node->right = NULL;
    return node;
}


// Main function to demonstrate delete operation
int main() {
    struct TreeNode* root = newNode(5);
    root->left = newNode(3);
    root->right = newNode(6);
    root->left->left = newNode(2);
    root->left->right = newNode(4);
    root->right->right = newNode(7);

    // Deleting a node
    root = deleteNode(root, 3);

    // The root now should be the root of the BST with the node with value 3 deleted.
    
    return 0;
}
