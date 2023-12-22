#include <stdlib.h>
#include <stdio.h>

typedef struct node{
  int data;
  struct node* next;
} node_t;

void deleteNode(node_t * head) {
  node_t * itr = head;
  // dummy head, we don't care about the data here
  node_t * prev = malloc(sizeof(node_t));
  prev -> next = itr;
  while (itr->data !=2) {
    itr = itr -> next;
    prev = prev -> next;
  }
  prev->next = itr->next;
  free(itr);
}

int main(){
  node_t *head;

  head = malloc(sizeof(node_t));
  head -> data = 1;
  node_t *n1 = malloc(sizeof(node_t));
  n1 -> data = 2;
  node_t *n2 = malloc(sizeof(node_t));
  n2 -> data = 3;
  n2 -> next = NULL;

  head->next = n1;
  n1 -> next = n2;
  n2 -> next = NULL;
  return 0;
}
