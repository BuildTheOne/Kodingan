#include <stdio.h>

int main(){

int age;
printf("Input Your Age");
scanf( "%d", &age);

if (age<17){
printf("Masih Muda");
}
else if(age>=17){
printf("Udah Tua");
}
return 0;
}
