#include <stdio.h>

int main(void){
  int grade[] = {10, 20, 30, 40, 50};
  int total = 0;
  for(int i = 0; i < sizeof(grade)/sizeof(grade[0]) + 1; i++){
    total += grade[i];
    printf("grade[%d] = %d\n", i, grade[i]);
  }
  int average = total / (sizeof(grade)/sizeof(grade[0]));
  printf("average = %d\n", average);

  return 0;
}