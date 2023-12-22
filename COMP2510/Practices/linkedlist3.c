#include <stdlib.h>
#include <stdio.h>

typedef struct node{
  int data;
  struct node* next;
} node_t;

typedef struct ll{
  node_t *head;
  node_t *tail;
} ll_t;

void addNode(int n) {
  node_t *new = malloc(sizeof(node_t));
  new->data = n;
  node_t *prev = malloc(sizeof(node_t));
  prev->next = head;

}

void deleteNode(int n){
    node_t *prev = malloc(sizeof(node_t));
    prev->next = head;
    node_t *itr = malloc(sizeof(node_t));
    itr = head;
    while(itr->data !=n){
      itr = itr->next;
      prev = prev ->next;
    }
    prev->next = itr->next;
    free(itr);
}

int main(){
  ll_t *list = malloc(sizeof(ll_t));
  list->head->next = tail;
  list->tail = null;
  return 0;
}
