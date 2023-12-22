#include <stdlib.h>
#include <stdio.h>

typedef struct node{
  int value;
  struct node* next;
} node_t;

void printlist(node_t *head) {
  node_t *temp = head;
  while(temp!=NULL) {
    printf("%d - ", temp->value);
    temp = temp->next;
  }
  printf("\n");
}

void appendNode(node_t *head, int n){
  node_t *itr = head;
  while(itr->next!=NULL){
    itr = itr->next;
  }
  node_t new;
  itr->next = &new;
  new.value = n;
  new.next = NULL;
}

int main(){
  node_t n1, n2, n3;
  node_t *head;

  n1.value = 45;
  n2.value = 8;
  n3.value = 32;

  head = &n3;

  n3.next = &n2;
  n2.next = &n1;
  n1.next = NULL;
  appendNode(head, 0);
  printlist(head);

  return 0;
}
