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


void swapNode(int i1, int i2){
  node_t* itr1, itr2 = head->next;
  node_t* prev1, prev2 = head;
  while(i1 > 1) {
    itr1 = itr1->next;
    prev1 = prev1->next;
    i1--;
  }
  while(i2 > 1){
    itr2 = itr2 -> next;
    prev2 = prev2->next;
    i2--;
  }
  dummy = itr2->next;
  prev-1->next = itr2;
  itr2->next = itr1->next;
  itr1->next = dummy;
  
}

int main(){
  ll_t *list = malloc(sizeof(ll_t));
  list->head->next = tail;
  list->tail = null;
  return 0;
}
