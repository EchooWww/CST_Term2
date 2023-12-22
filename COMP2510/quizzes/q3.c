#include <stdio.h>
#include <string.h>

int bitMulti(int a, int b) {
    int ans = 0, tempa = a, tempb = b;
    while (tempb > 0) {
        int lowBit = (tempb & 1);
        if (lowBit > 0) {
            ans += tempa;
        }
        tempa = tempa << 1;
        tempb = tempb >> 1;
    }
    return ans;
}

void multiplyNums(char* s1, char* s2, char* ansHex) {
    int n1 = strlen(s1), n2 = strlen(s2);
    int ans[128] = {0};
    int d1[n1], d2[n2];
    for (int i = 0; i < n1; ++i) {
        char temp = s1[i];
        int num;
        if (temp >= '0' && temp <= '9')
            num = temp - '0';
        else if (temp >= 'A' && temp <= 'F')
            num = temp - 'A' + 10;
        else {
            strcpy(ansHex, "Error in input.");
            return;
        }
        d1[n1 - i - 1] = num;
    }
    for (int i = 0; i < n2; ++i) {
        char temp = s2[i];
        int num;
        if (temp >= '0' && temp <= '9')
            num = temp - '0';
        else if (temp >= 'A' && temp <= 'F')
            num = temp - 'A' + 10;
        else {
            strcpy(ansHex, "Error in input.");
            return;
        }
        d2[n2 - i - 1] = num;
    }
    for (int i = 0; i < n1; ++i) {
        for (int j = 0; j < n2; ++j) {
            int temp = bitMulti(d1[i], d2[j]);
            ans[i + j] += temp;
        }
    }
    for (int i = 0; i < n1 + n2 - 1; ++i) {
        int carry = ans[i] >> 4;
        int resi = ans[i] & 15;
        ans[i + 1] += carry;
        ans[i] = resi;
    }
    int highPos = n1 + n2 - 1;
    while (ans[highPos] == 0)
        highPos--;
    for (int i = highPos; i >= 0; --i) {
        if (ans[i] < 10)
            ansHex[highPos - i] = (ans[i] + '0');
        else
            ansHex[highPos - i] = (ans[i] - 10 + 'A');
    }
    ansHex[highPos + 1] = '\0';
}

int main() {
    char s1[] = "123456789ABCDEF0", s2[] = "123456789ABCDEF0";
    char ans[128];
    multiplyNums(s1, s2, ans);
    printf("%s\n", ans);
    return 0;
}
