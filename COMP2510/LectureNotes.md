### Libraries

syntax: `#include <library_name>`
e.g. `#include <stdio.h>`
`stdio.h` is a library that contains functions for input and output.

### hello.c Hello World

- for main method, the convention is, always return an integer.
- It's okay to have void main, but it's not a good practice. C would actually let us do anything we want, even it's unsafe like array out of bound, but it's not a good practice.
- The code can compile doesn't mean it's correct. It's just syntactically correct.

```c
int main(){
    printf("Hello World\n");
    return 0;
    // By convention, 0 means success, 1 means failure
}
```

- C lets us do anything we want, even print something uninitialized.But we don't want to do that.

### To compile and run

1. `gcc hello.c` to compile to a executable file. The default output file name is `a.out`.

```bash
## specify the output file name
gcc hello.c -o hello
```

2. `./hello` to execute. The `./` means current directory, it is necessary to run the executable file, because if not, the system will regard it as a command.

```bash
## will print Hello World
./hello
## returned 0 won't print anything
```

### Arguments

```c
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {

	int n, m;
	n = atoi(argv[1]);
	m = atoi(argv[2]);
	printf("Argument 1: %d \nArgument 2: %d \n", n, m);
	return 0;
}
```

- stdlib.h: standard library, contains functions like atoi
- atoi: convert a string to an integer
- argc: argument count, the number of arguments
- argv: argument vector, an array of strings, each string is an argument

> It's important to remember that argc is always one more than the number of command line arguments, since it also includes the name of the program in argv[0]
> Also, remember that argv[0] is a string, not an integer, so you can't use atoi() on it.

### Integer and Float

If we want to print a float, we need to use `%f` instead of `%d`. But it will print just 0.00000. Coz float takes

### Booleans in C

- C doesn't have a boolean type. Instead, it uses integers. 0 means false, and anything else means true.
- if (<condition>) checks if the condition is none-zero, and executes the code inside the block if it is.
- The default value of a true expression is 1, and the default value of a false expression is 0. so `int c = 5==5` will assign 1 to c.

### Ternary Operator

```c
int c = (5==5) ? 1 : 0;
```

### Switch Statement

```c
switch(month){
    case 1:
        printf("January\n");
        break;
    case 2:
        printf("February\n");
        break;
    default:
        printf("Invalid month\n");
        break;
}
```

### Loops

for loops are better for compiling time, while while loops are better for running time.

## Arrays

- Collections or groups of the **same data type**
- Can be multi-dimensional
- You can have an array of any type in C: including structs, pointers, etc. It basically means a contiguous block of memory.

### Declaring an array

- The length of the array can be declare by putting the lenth in the square bracket[], like `int arr[3]`. It's hard to resize, because c will allocate the memory for the array when it's declared. The numbers are not initialized to 0 like in Java.
- We can also declare an array without specifying the length, like `int arr[]`. But we need to initialize it when we declare it, like `int arr[] = {1, 2, 3}`. If we don't initialize it, it will be an empty array.
- Or declared with `int[5] = {1, 2, 3}`, the uninitialized elements will be garbage values.

> Array is the only built-in data structure in C. For other data structures, we need to implement them ourselves.

### 2D Arrays

A 2d-array `int[m][n]` is an array of m arrays of size n. if you try to access it with `num[0]`, it will return the first array, which is an array of size n.

|      | col1        | col2        | col3        |
| ---- | ----------- | ----------- | ----------- |
| row1 | `num[0][0]` | `num[0][1]` | `num[0][2]` |
| row2 | `num[1][0]` | `num[1][1]` | `num[1][2]` |
| row3 | `num[2][0]` | `num[2][1]` | `num[2][2]` |

- Declare 2d array like `int x[2][2] = {4,5,8,2};` or `int x[2][2] = {{4,5},{8,2}};` are both okay. Coz there's actually no 2 dimensions in the memory.
- It's also okay to declare like `int x[][2] = {4,5,8,2};`, but we need to specify the second dimension.

```c
#include <stdio.h>

int main() {

    // x[1][1] and x[0][3] are the same, because they are both the 4th element in the memory
    // but x[1][1] is still more recommended
  int x[2][2] = {4,5,8,2};
  printf("%d\n", x[1][1]);
  return 0;
}
```

- We access the elements in the 2d array columnwise, which is faster than rowwise, because of the way the memory is allocated: elements in the same row are stored together, then the next row, and so on. When we access something in the cache, it will also load the next few elements in the same row, so it's faster to access the next element in the same row. There's no difference in big O notation, but there's a difference in running time.

- In c, integers are 4 bytes, floating points are 8 bytes. C won't prevent us from storing a float in an integer array. But as floats are stored as IEEE 754 (1 bit sign, 8 bit exponent, 23 bit mantissa). So if you put a 3.14 in an integer array, it's stored as 3.14 \* 10^0, so the first 2 bytes are sign + exponent.

- We can get the elements in the array by dividing the sizeof the array by the sizeof the element. e.g. `sizeof(x)/sizeof(x[0])` will return the number of rows in the array.

> **C doesn't check the bound**. If we try to **access** some out of bound elements, it will access some **garbage** values. But if we try to **assign** some out of bound elements, it will throw an run time error (instead of compile time error).

- C is much faster than Java, because it works on the native hardware, while Java works on the JVM. C is the only we can use to write an operating system.

### Caution!

- It's the programmer's responsibility to make sure that the array is not out of bound, which likely causes a segmentation fault.

### Declaring arrays

- Outside of any function: always static
- define a static variable: `#define SIZE 10`
- static data will be allocated at runtime, under the heap. When we compile, only program code exists.
- Stack overflow: when stack and heap collide, it will cause a stack overflow. When we call a function, stack goes downward, and when we return, stack goes upward. So if we have a recursive function, it will cause a stack overflow if we don't have a base case.
- Variables inside the functions are also in the stack.
- SP: stack pointer, points to the **top** of the stack. When we call a function, SP goes down, when we return, SP goes up.
- We don't wanna global variable for space effeciency. Static data is there for the entire program until we kill it, even if we don't use it.
- Stack is released when the function returns, that's also why it's better to have a modular design in C
- Dynamic allocated array uses the heap, which is much bigger than the stack. But it's slower than static array, because it needs to be allocated at runtime.

## Memory Organization

- Memories are organized as sequence of numbered bytes. Many are linear, but some are not.

- Larger data types are sequences of bytes. e.g. int is 4 bytes, float is 4 bytes, double is 8 bytes, char is 1 byte.
- Big endian or little endian: the way the bytes are stored in the memory. e.g. 0x12345678, big endian will store it as 0x12 0x34 0x56 0x78, while little endian will store it as 0x78 0x56 0x34 0x12. It's important to know the endianess when we are working with binary files.

## Pointers

When we see the `*` in C, it can mean 2 things:

- multiplication
- pointer
- Pointer is the most important concept in C. It's a variable that stores the address of another variable.

- In lab2, when we pass the original array to the other function, we're creating a new copy, and occupied twice space of memory.
- How can we pass the original array to the function without creating a new copy?: use pointers

Assumuing we're having a 32-bit machine, everything is 4 bytes, so the pointers are also 4 bytes.

Pointers in C is used everywhere: arrays, strings, functions, etc.

### Definition of pointer

A value indicating the value of (the first byte of) a data object

### syntax

- `int *p;` declare a pointer
- `&`: return the address of a variable

```c
int *p; // declare a pointer
int x = 5;
p = &x; // assign the address of x to p
printf("%p\n", p); // print the address of x 610021692
```

- `*` dereference operator: return the value of the variable that the pointer is pointing to. It can only be used on a pointer.

```c
int x = 5;
int *p = &x;
printf("%d\n", *p); // print the value of x 5
```

- It gives us the power of accessing the memory directly, which is very powerful, but also very dangerous.
- We don't hard code the address, instead we pass the pointer as an argument to the function, because address is changing all thetime

```c
void foo(int *ptr) {
  *ptr = 4;
  return;
}
int main(int argc, char **argv) {
  int x = 3;
  int *ptr = &x; // int * is the type of ptr, not dereferencing
  foo(ptr);
  printf("%d\n", x);
  return 0;
}
```

### Types of pointers

- int \*p: pointer to an integer
- char \*p: pointer to a character
- char \*\*p: pointer to a pointer to a character
- int \*p[5]: array of 5 pointers to integers
- type \*p: pointer to an object of type

### Pointer arithmetic

- `p++`: increment the pointer by the size of the type it points to, not by 1 byte. e.g. if p is a pointer to an integer, p++ will increment p by 4 bytes: That's why we specify the type of the pointer when we declare it.
- This is what pointer arithmetic is about

### Arrays and pointers are almost the same

- There's no array type in C, array is just a pointer to the first element of the array. So we can use the pointer to access the array, and we can also use the array to access the pointer.
- Internally, when we declare an array, C will allocate a block of memory, and the name of the array is the address of the first element of the array. `int A[10]`, A is the address of the first element of the array.

- if we do it like `char c[10]`, and then `int *p = c`, and we increment p, it will increment by 4 bytes, because p is a pointer to an integer, not a pointer to a character. So it's not safe to do that.
- The only difference between `int a[10]` and `int *p` is that a is 40 bytes, because we allocate the memory of the array;while p is 4 bytes. But they are both pointers to the first element of the array.
- But if we pass an array to a function, it will be converted to a pointer, so we can't get the length of the array inside the function.

### Double pointer

```c
int main() {
  char c[2];
  c[0]='h';
  c[1] = 'h';

  char **ptr = &c;
  printf("%d\n", ptr);

  return 0;
}
```

### String

strcpy() is a function in C library to copy one string to another. The first argument to strcpy() is destination string, second argument is source string. The destination string should be large enough to store the source string. (2 char pointers)

- we cannot have dynamic array in C, so we need to specify a constant for the length of the string. e.g. `char str[10] = "hello";. we cannot allocate memory for array at the runtime in the stack, this will cause an error. That's why we use heap and dynamic memory allocation.

## Lecture 3

## Memory archtechture

There are 4 parts of memory:

- Code: the code of the program
- Static data: global variables, static variables
- Heap: dynamic memory allocation
- Stack: local variables, function calls

### Stack

- We we call a function, a stack frame is created, and when we return, the stack frame is cleared. The stack frame contains the local variables, and the return address.
- The stack is a stack: the last in, first out. So when we call a function, the stack pointer goes down, and when we return, the stack pointer goes up.
- When the program start, the OS allocates a block of memory for the stack. But the actual allocation of stack frame and local variables are done at runtime. If we need more memory than the stack can provide(usually because of too many function calls), it will cause a stack overflow.
- That's also why we can only have fixed size array in C, so the OS can allocate the needed memory to our stack.

### Heap

- Unlike stack, the heap is not fixed size. It's a pool of memory that we can allocate and deallocate memory at runtime. It can grow as long as we have memory in the system, which is also dangerous!
- Heap is also called dynamic memory: it's not LIFO, we can allocate and deallocate memory in any order.

### Dynamic memory allocation

- malloc() and free() are the functions to allocate and deallocate memory in the heap, which are part of `<stdlib.h>`, the return type is a void pointer
- malloc(): asking the OS for a block of memory of a certain size, but you can fail to allocate memory(because malloc ask for continous memory). When it fails, it will return a null pointer.
- calloc() works a bit differently: it will initialize the memory to 0, and it takes 2 arguments: the number of elements, and the size of each element.
- realloc(): change the size of the memory block. It takes 2 arguments: the pointer to the memory block, and the new size. It will return a pointer to the new memory block. If it fails, it will return a null pointer, and the original memory block will not be changed. If we put the second argument as 0, it will free the memory block.
- free(): return the memory pointed by the pointer. Must have been allocated by malloc() or realloc(). If you try to free a pointer that's not allocated by malloc(), or already freed, it will cause an error.
- Memory allocation is done in runtime, and the programmer is responsible for garbage collection.

```c
int main(int argc, char **argv) {
  char *string1;
  int n, i;
  printf("How many chars in the string? ");
  scanf("%d", &n);
  string1 = (char *) malloc(sizeof(char)*n);
  printf("type your string: ");
  scanf("%s", string1);
  printf("%s\n",string1);
  free(string1);
  // free it for more than once would cause error. Memory leak problem
  // forgetting to free it cause memory leak
  free(string1);
}
```

- calloc() is a variant of malloc(), it takes 2 arguments: the number of elements, and the size of each element. It will initialize the memory to 0. It's safer than malloc(), because it's less likely to cause memory leak. malloc() is more often used, because it's faster.

#### Typical usage of malloc() and free()

Usually, we want to use malloc() in the helper function, and free() in the main function. Because if we use malloc() in the main function, we need to pass the pointer to the helper function, and it's hard to free it in the helper function.

## Lecture 4

### Recursion

Example: factorial

- Recursion is a way of solving a problem by breaking it down into smaller problems of the same type until we reach a base case.
- Base case: the smallest problem that we can solve directly
- In c, we cannot do a recursive call within the main function.

```c
// doing factorial iteratively
long factorial(int n) {
  long result = 1;
  for (int i = 1; i <= n; i++) {
    result *= i;
  }
  return result;
}
```

```c
// return the index of we found it
int binarySearch (int arr[], int l, int r, int x){
    if (l <= r) {
      int mid = (l + r) /2;
      if (arr[mid] == x) return mid;
      if (arr[mid] > x) return binarySearch(arr, l, mid-1,x);
      return binarySearch(arr, mid+1, r,x);
    }
    return -1;
}
```

integer range [1, n]
array size (n+1), including duplicates
n = 3;
[do a binary search for the candidates]

```c
int find_dup(int arr[], int n){
  int l = 0, r = n;
  if (l < n) {
    if (arr[l] == arr[r]) return arr[l];

  }
}

```

## Lecture 5

- How many computations will this program perform to get the number.
- When we talk about time complexity, we always assume when the array size is very large, so we don't care about the constant factor.

### Caching

- Store the most frequently used subset of data in a fast memory, instead of visit the big dataset every time.
- This has nothing to do with the algorithm big O, but it can make a huge difference in the runtime.
- So we don't care about the actual runtime when we're talking about big O, we only care about the growth rate of the runtime.

### O(logn)

When we use logn in big o, it basically means base 2. Because log2n = logn / log2, and log2 is a constant, so we can ignore it.

### Discussing different cases

- Best case: the best case scenario, the best input, we don't care about it in big O
- Worst case: the worst case scenario, the worst input, we care about it in big O
- Average case: the average case scenario, the average input, we don't care about it in big O

## Structures in C

- In C, it's our job to make the data structures on our own.
- struct is a way to group data together, it's a user-defined data type.

### Syntax

```c
struct student {
  char *name;
  int age;
  int id;
};
int main(int argc, char **argv) {
  struct student s1;
  s1.name = "John";
  s1.age = 20;
  s1.id = 123456;
  printf("%s\n", s1.name);
  return 0;
}
```

- a easier way to do it is to use typedef

```c
typedef struct {
  char *name;
  int age;
  int id;
} student;

int main(int argc, char **argv) {
  student s1; // here the system give you the whole chuck of memory that a student needs
  // s1.name = "John"; // the equal operator doesn't work on pointers, so we need to use ->
  s1.name -> "John";
  s1.age = 20;
  s1.id = 123456;
  printf("%s\n", s1.name);
  return 0;
}
```

- We can assign a struct to another struct (it's actually copying the values in struct2 to struct1), but we cannot compare 2 structs with ==, because == is used to compare the address of the structs, not the content of the structs.
- When we're passing a struct as parameter in a function call, it's passed by value, so it's actually copying the struct.

### Singly linked list

## Lecture 6

### Unions

- Unions allow us to allocate the same memory for different data types. It's like a struct, but all the fields share the same memory. So we can only use one field at a time.
- In the real life, unions are more frequently used than malloc(), because it's faster, and doesn't ask for contiguous memory. (Big contiguous memory is hard to find when the memory is fragmented)

### Linked List

## Lecture 7 Sorting Algorithms

TODO: Study: heap sort

We can never get better than O(n) in terms of time complexity, because we need to visit every element in the array.

### Insertion sort

It's not actually commonly used, but it's a good way to understand the sorting algorithms.

- A portion of the array is sorted, and the rest is unsorted.
- Key: the element we're trying to insert
- First element is always sorted, so we start inserting from the second element.
- Time complexity: O(n^2), space complexity: O(1)
- Best case: Ω(n), when the array is already sorted, the inner loop is skipped.
- Average case: Θ(n^2)(actually(n^2+n)/2, still O(n^2)), because the inner loop is executed half of the time.

```c
int key y;
int n = 10;
int arr = {2, 0, 3, 1, 9, 8, 7, 6, 5, 4};

for(int x = 1; x < n; x++){
    key = arr[x];
    int y = x - 1;
    while(y >= 0 && arr[y] > key){
        arr[y+1] = arr[y];
        y--;
    }
    arr[y+1] = key;
}
```

### Selection sort

- Simple to implement, but not efficient
- For every iteration, we find the smallest element in the unsorted portion, and swap it with the first element of the unsorted portion.
- Unlike insertion sort, at first, we regard the whole array as unsorted
- Insertion sort: you know the key, and you don't know where to insert it
- Selection sort: you don't know the key, but you know where to put it
- Time complexity: O(n^2), space complexity: O(1), best case: Ω(n^2), average case: Θ(n^2)

```c
int y, min;
int n = 10;
int arr = {2, 0, 3, 1, 9, 8, 7, 6, 5, 4};
for (int x = 0; x < n; x++) {
  int min = x;
  for (y = x + 1; y < n; y++) {
    if (arr[y] < arr[min]) {
      min = y;
    }
  }
  int temp = arr[x];
  arr[x] = arr[min];
  arr[min] = temp;
}
```

### Merge sort

- Divide and conquer: Divide the array into 2 halves, sort each half, and merge them together, recursively.
- Divide: base case: when the array size is 1, it's sorted.
- Conquer: merge the 2 sorted arrays together, with 2 pointers.
- We need an auxiliary array to store the sorted array, and then copy it back to the original array.
- After we exhuast one of the arrays, we copy the sorted part of axuiliary array back to the original array. (Optimization strategy: it's better than copy the original to axuiliary and copy it back, although won't make a difference in big O)

Staiblity: in sorting algorithms, if we have 2 elements with the same value, and we sort them, it's when the stability comes into play. If the order of the 2 elements are preserved, it's a stable sorting algorithm. Which means what comes first in the original array will come first in the sorted array.

- Selection sort is not stable, because we update the min to the last occurance of the min.
- Worst case: Θ(nlogn), space complexity: O(n)

TODO:

```c
int[]arr = {8,7,6,5,4,3,2,1,0,0};

void mergeSort(int[] arr, int n) {
  if (n > 0) {
    int left[n / 2], right[n - n / 2];
    for (int i = 0; i < n / 2; i++) {
      left[i] = arr[i];
    }
    for (int i = n / 2; i < n; i++) {
      right[i - n / 2] = arr[i];
    }
    mergeSort(left, n / 2);
    mergeSort(right, n - n / 2);
    merge(left, n / 2, right, n - n / 2, arr);
  }
}

void merge(int[] left, int n1, int[] right, int n2, int[] arr) {
  int i = 0, j = 0
  for (int k = 0; k < n1 + n2; k++) {
    if(i2>=n2 || (i1<n1 && left[i1] <= right[i2])){
      arr[k] = left[i1];
      i1++;
    } else {
      arr[k] = right[i2];
      i2++;
    }
}
```

### Quick sort

Quick sort is often faster than merge sort, because it doesn't need to allocate extra memory. But it's not stable.

- it is another divide-and-conquer algorirthm
- Divide: pick a pivot, and partition the array into 2 parts: the left part is smaller than the pivot, and the right part is larger than the pivot. (The pivot is in the right position)
- Conquer: recursively sort the 2 parts

```c
int* QuickSort(int* arr, int n) {
  if(n<2) return arr;
  int pivot = arr[0];
  int* left, right;
  for (int i = 1; i <=n; i++) {
    if (arr[i] < pivot) {
      add_end(left, arr[i]);
    } else {
      add_end(right, arr[i]);
    }
  }
  left = QuickSort(left, len(left));
  right = QuickSort(right, len(right));
  add_end(left, pivot);
  return add_end(left, right);
}
```

- Choosing a pivot is critical: if we choose the first element as the pivot, and the array is already sorted, it will be the worst case, which is O(n^2). So we need to choose the pivot randomly.
- Repeated elements: if we have a lot of repeated elements, it also slows down the quick sort.
- It is also not stable, because we swap the elements.
- Worst case: Θ(n^2), best case: Ω(nlogn), average case: Θ(nlogn)
- Why we use quick sort even though it's no better in big O and even unstable? Because it is better for cache locality: it has a higher hit rate in cache, because it's more likely to access the elements that are close to each other.
- Also, merge sort does a lot of memory copy, which is not considered in big O, but it is expensive and slow.

Sorting Big-O
| Algorithm | Best Case | Worst Case | Average Case | Space Complexity | Stable |
| --- | --- | --- | --- | --- | --- |
| Insertion Sort | Ω(n) | O(n^2) | Θ(n^2) | O(1) | Yes |
| Selection Sort | Ω(n^2) | O(n^2) | Θ(n^2) | O(1) | No |
| Merge Sort | Ω(nlogn) | O(nlogn) | Θ(nlogn) | O(n) | Yes |
| Quick Sort | Ω(nlogn) | O(n^2) | Θ(nlogn) | O(logn) | No |

### Tim sort

A hybrid sorting algorithm, which is a combination of merge sort and insertion sort. It's used in Python, Java, etc.

- Best case: when the array is already sorted, it's O(n)
- Worst case: when the array is reverse sorted, it's O(n) (just reverse the array)
- random case: merge sort, O(nlogn)
- For merge sort, timsort doesn't do it all the way down to 1 element, it does it to a certain threshold "run", and then use insertion sort to sort the runs, and then merge them together.

## Lecture 8: Bitwise Operations

- Write a function to check if the thrid bit from right is 1 or 0

```c
int check(int n){
    int x = ob100;
    return n & x == 4 ? 1:0;
}
```

- Bit masking: use bitwise operations to manipulate bits. Combined with &, |, ^ we can do a lot of things with bitwise operations.

- Write a function that turns "on" the first and third bit from the right of an integer

```c
int turnOn(int n) {
  int x = 5;
  return n | x;
}
```

3 encoding ways to represent a set of cards:

- Among them, the best way is to use 6 bits to represent a set of cards, because it's the most space efficient way.
- 2 bits for the suit, 4 bits for the rank

```c
int isSameSuit(char card1, char card2) {
  return (card1 & 0x30) == (card2 & 0x30);
}
// without equal sign: flip the XOR
int isSameSuit(char card1, char card2) {
  return !((card1 & 0x30) ^ (card2 & 0x30));
}

int compareValue(char card1, char card2) {
  return ((unsigned char)(char1 & 0x0F)) > ((unsigned char)(char2 & 0x0F));
}
```

Encoding Integers

- The hardware support 2 flavors of integers: signed and unsigned
- Signed integers: the first bit is the sign bit, 0 means positive, 1 means negative. The rest of the bits are the magnitude of the number.
- Unsigned integers: all the bits are the magnitude of the number.

Designate the high-order bit(MSB) as the sign bit

2's complement: the most common way to represent negative numbers in any machine. We flip and add 1 to the absolute value of the number.

- We flip the positive number, and add 1 to it, we get the negative number.

```c
void main() {
  int x = -100;
  printf("%d\n", x);
  printf("%u\n", x); // will print 4294967196
}
```

- Sign extension: Copy the sign bit to the left, to fill the rest of the bits.

### shift operators

- <<: left shift, 1011 <<1 becomes 0110 (pad with 0)
- > > : right shift, 1011 >>1 becomes 1101 (pad with the original sign bit: logical shift for unsigned, arithmetic shift for signed)

Corner cases:

- shift negative numbers: undefined behavior (depends on the compiler)

? Question
A nums array, contains n distinct numners in [0, n]. but there's one missing number. Find the missing number with bit operations

```c
// we are taking advantage of the property that x ^x = 0;
// so the missing number will be the result of the XOR of all the numbers in the array and all the numbers from 0 to n
int findMissingNum (int* nums; int numSize) {
  int result = 0;
  for (int i = 0; i < numSize; i++) {
    result ^= nums[i];
  }
  for (int i = 0; i <= numSize; i++) {
    result ^= i;
  }
  return result;
}
```

## Lecture 9: Generics In C

- C is a statically typed language, which means the type of the variable is determined at compile time.
- We can make a generic function in C, but we need to specify the type when we call the function.

Example: swap

```c
void swap(int* a, int* b) {
  int temp = *a;
  *a = *b;
  *b = temp;
}
// to make it generic
void swap (void* a, void* b) {
  void temp = a;
  a = b;
  b = temp;
}
// But the problem is, the system doesn't know the size of the type, so we need to pass the size of the type as an argument
void swap (void* a, void* b, size_t size) {

  // We use a char pointer to point to the address of the void pointer, doesn't matter what type it is, because char is 1 byte
  char temp[size];

  // You cannot dereference a void pointer to copy. So we need to use memcpy
  memcpy(temp, a, size);
  memcpy(a, b, size);
  memcpy(b, temp, size);

  // or we can iterate through the size
  for (int i = 0; i < size; i++) {
    char temp = ((char*)a)[i];
    ((char*)a)[i] = ((char*)b)[i];
    ((char*)b)[i] = temp;
  }
}
  // a generic function to swap the first and last element of an array
  void swap_end(void* arr, size_t nelems, size_t elem_size) {
    // temp is a char pointer to the address of the void pointer, with size of elem_size
    char[elem_size] temp;
    // copy the first element to temp
    memcpy(temp, arr, elem_size);
    // copy the last element to the first element
    memcpy(arr, arr + (nelems - 1) * elem_size, elem_size);
    // copy the temp to the last element
    memcpy(arr + (nelems - 1) * elem_size, temp, elem_size);

    // or we call the swap function
    // Its necessary to increment the pointer by the size of the element, because the pointer is a void pointer, and we need to tell the system the size of the element
    // If we specify the data type, it's the system's job to increment the pointer by the size of the element
    // We also need to cast the void pointer to a char pointer, because void pointer cannot be incremented
    swap(arr, (char*)arr + (nelems - 1) * elem_size, elem_size);
  }

```

### memmove

memmove is the same as memcpy: it copies the memory from one place to another. But it supports overlapping memory blocks, while memcpy doesn't, i.e., it's safe to use memmove when the source and destination memory blocks overlap.

### Stack

stack is implemented with linked list, so it's not contiguous in the memory. It's a LIFO data structure.

```c
typedef struct node {
  int val;
  struct node* next;
} node;

typedef struct stack {
  node* top;
  int size;
} stack;

void push(stack* s, int data) {
  node* new_node = (node*)malloc(sizeof(node));
  new_node->data = data;
  new_node->next = s->top;
  s->top = new_node;
  s->size++;
}

int peek (stack* s) {
  if (s->top == NULL) {
    printf("Empty stack\n");
  } else {
    return s->top->val;
  }
}

int pop(stack* s) {
  if (s->size == 0) {
    printf("Empty stack\n");
  } else {
    int result = s->top->val;
    node* temp = s->top;
    s->top = s->top->next;
    free(temp);
    s->size--;
    return result;
  }
}

int count(stack* s) {
  return s->size;
}

```

### valid parentheses

```c
int isValid(char* s) {
  int len = strlen(s);
  if (len % 2 != 0) return 0;
  stack* st = (stack*)malloc(sizeof(stack));
  st->top = NULL;
  st->size = 0;
  for (int i = 0; i < len; i++) {
    if (s[i] == '(' || s[i] == '[' || s[i] == '{') {
      push(st, s[i]);
    } else {
      if (st->size == 0) return 0;
      char top = peek(st);
      if (s[i] == ')' && top != '(') return false;
      if (s[i] == ']' && top != '[') return false;
      if (s[i] == '}' && top != '{') return false;
      pop(st);
    }
  }
  return st->size == 0;
}
```

### Practice

Given string of non-negative number n and integer k, return the smallest number that can be created by removing k digits from n.

```c
int removeKdigits(char* number, int k) {
  int len = strlen(number);
  if (k == len) return 0;
  stack* st = (stack*)malloc(sizeof(stack));
  st->top = NULL;
  st->size = 0;
  for (int i = 0; i < len; i++) {
    while (k > 0 && st->size > 0 && peek(st) > number[i]) {
      pop(st);
      k--;
    }
    push(st, number[i]);
  }

  while (k > 0) {
    pop(st);
    k--;
  }
}
```

### Generic stack

```c
typedef struct stack {
  void* data;
  struct stack* next;
  size_t count;
  size_t elem_size;
} stack;

stack *stack_init(size_t elem_size) {
  stack* s = (stack*)malloc(sizeof(stack));
  s->data = NULL;
  s->next = NULL;
  s->count = 0;
  s->elem_size = elem_size;
  return s;
}

void push(stack* s, void* data) {
  stack* new_node = (stack*)malloc(sizeof(stack));
  new_node->data = malloc(s->elem_size);
  memcpy(new_node->data, data, s->elem_size);
  new_node->next = s->top;
  s->top = new_node;
  s->count++;
}

void pop(stack* s, void* data) {
  if (s->count == 0) {
    error("Empty stack");
  } else {
    memcpy(data, s->top->data, s->elem_size);
    stack* temp = s->top;
    s->top = s->top->next;
    // we need to free the data in the node, because we allocated memory for it when we push it
    free(temp->data);
    free(temp);
    s->count--;
  }
}
```

Binary Search Tree

- An array is easy to search, but it's hard to insert and delete
- A linked list is easy to insert and delete, but it's hard to search
- Binary search tree is a data structure that's easy to search, insert and delete
- In real life, we actually use n-ary search tree, because it's more of the real life scenario

### Binary Search Tree

Think about it: How can we skip some levels in a tree?

? what if we have duplicate values in the tree?

- We can either put it in the left subtree, or the right subtree, but the corner case is, if we have a lot of duplicate values, it will be a linked list, which is not good.

- search(contains())

```c
int contains(TreeNode* node, int val) {
  if (node == NULL) return 0;
  if (node->val == val) return 1;
  if (node->val > val) return contains(node->left, val);
  return contains(node->right, val);
}
```

- getMin()

```c
int getMin(TreeNode* node) {
  if (node->left == NULL) return node->val;
  return getMin(node->left);
}
```

- getMax()

```c
int getMax(TreeNode* node) {
  if (node->right == NULL) return node->val;
  return getMax(node->right);
}
```

- add(): we always insert at the leaf node for BST. It's easy to implement, but can be really bad for the worst case, because it can be a linked list.

```c
void add(TreeNode* node, int val) {
  if (node == NULL) {
    node = (TreeNode*)malloc(sizeof(TreeNode));
    node->val = val;
    node->left = NULL;
    node->right = NULL;
  } else if (node->val > val) {
    add(node->left, val);
  } else {
    add(node->right, val);
  }
}
```

- free()

```c
void freeTree(TreeNode* node) {
  if (node == NULL) return;
  freeTree(node->left);
  freeTree(node->right);
  free(node);
}
```

- remove()

```c
void removeTree(Treenode *node, int val) {
  if ()
}
```

- traverse a tree

## Function Pointers

Function pointers are pointers that point to functions. It's a powerful feature in C.

- We can pass a function as an argument to another function
- We can return a function from another function

### Syntax

```c
int someFunction(int a, int b) {
  return a + b;
}

int main(int argc, char **argv) {
  int (*funcPtr)(int, int);
  // we can omit the & here, because the name of the function is the address of the function
  funcPtr = someFunction;
  printf("%d\n", funcPtr(1, 2));
  return 0;
}

```

- array of function pointers

```c
int add(int a, int b) {
  return a + b;
}
int sub(int a, int b) {
  return a - b;
}

int main(int argc, char **argv) {
  // only works for the same return type and same arguments
  int (*funcPtr)(int, int) = {add, sub};
  int index;
  scanf("%d", &index);
  int a = 1, b = 2;
  printf("%d\n", funcPtr[index](a, b));
  return 0;
}
```

- function pointer as an argument

```c
void CallFunc(int (*funcPtr)(int, int), int a, int b) {
  printf("%d\n", funcPtr(a, b));
}
```
