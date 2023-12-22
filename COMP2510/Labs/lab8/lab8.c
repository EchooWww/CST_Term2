# include <stdio.h>
# include <stdlib.h>

int detectOverflow (int bitwidth, int64_t num1, int64_t num2, int64_t *sum, int shift) {
  int64_t mask = INT64_C(1)<<(bitwidth - 1);
  int64_t sign1 = num1 & mask;
  int64_t sign2 = num2 & mask;
  int64_t signSum = *sum & mask;
  if (sign1 == sign2 && sign1 != signSum) {
    *sum = *sum >> shift;
    return 1;
  }
  return 0;
}

void logicalAdder(int bitwidth, int64_t num1, int64_t num2, int shift) {
  int64_t carry = num1 & num2;
  int64_t sum = num1 ^ num2;
  while (carry) {
    int64_t shiftedCarry = carry << 1;
    carry = sum & shiftedCarry;
    sum ^= shiftedCarry;
  }
  if (detectOverflow(bitwidth, num1, num2, &sum, shift)) {
    printf ("Result after right shift: %lld \nOverflow detected within the specified bitwidth. ", sum);
  } else {
    printf ("Result of addition: %lld", sum);
  }
}

int main(int argc, char *argv[]) {
  if (argc != 5) {
    printf("Usage: %s <bitwidth> <Number1> <Number2> <Shift Number>\n", argv[0]);
    exit(1);
  }
  int bitwidth = atoi(argv[1]);
  if (bitwidth != 8 && bitwidth != 16 && bitwidth != 32 && bitwidth != 64) {
    printf("Bitwidth must be 8, 16, 32, or 64\n");
    exit(1);
  }
  int64_t num1 = strtoll(argv[2], NULL, 10);
  int64_t num2 = strtoll(argv[3], NULL, 10);
  int shift = atoi(argv[4]);
  if (shift < 0 || shift > bitwidth) {
    printf("Shift must be between 0 and bitwidth\n");
    exit(1);
  }
  logicalAdder(bitwidth, num1, num2, shift);
  return 0;
}