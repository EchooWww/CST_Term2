#include <stdlib.h>
#include <stdio.h>

typedef struct node{
  int data;
  struct node* next;
  struct node* prev;
} node_t;

typedef struct ll{
  node_t *head;
  // a structure with a dummy tail
  node_t *tail;
} ll_t;

void addNode(int n) {
  node_t *node = malloc(sizeof(node_t));
  node->data = n;
  tail->prev->next=node;
  node->next = tail;
  node->prev = tail->prev;
  tail->prev = node;
}

void deleteNode(int n) {
  itr = head;
  while(itr->data !=n) itr= itr->next;
  itr->prev->next = itr->next;
  itr->next->prev = itr->prev;
  free(itr);
}

int main(){
  head->prev = null;
  head->next = tail;
  tail->prev = head;
  tail->next = null;
  return 0;
}
