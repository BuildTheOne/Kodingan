#include <stdio.h>

void swap(int *xp, int *yp)
{
  int temp = *xp;
  *xp = *yp;
  *yp = temp;
}

void sort(int arr[], int n)
{
  int i, j, min_idx;

  for (i = 0; i < n - 1; i++)
  {

    min_idx = i;
    for (j = i + 1; j < n; j++)
      if (arr[j] < arr[min_idx])
        min_idx = j;

    swap(&arr[min_idx], &arr[i]);
  }
}

int deleteDuplicate(int arr[], int n)
{

  if (n == 0 || n == 1)
    return n;

  int temp[n];

  int j = 0;
  for (int i = 0; i < n - 1; i++)

    if (arr[i] != arr[i + 1])
      temp[j++] = arr[i];

  temp[j++] = arr[n - 1];

  for (int i = 0; i < j; i++)
    arr[i] = temp[i];

  return j;
}

void main(int argc, char *argv[])
{
  int arrSort[argc];
  for (int i = 1; i < argc; i++)
  {
    arrSort[i - 1] = strtol(argv[i], NULL, 10);
  }
  sort(arrSort, argc - 1);
  int n;
  n = deleteDuplicate(arrSort, argc - 1);
  printf("%d %d %d ", arrSort[0], arrSort[1], arrSort[2]);
  printf("%d %d %d", arrSort[n - 3], arrSort[n - 2], arrSort[n - 1]);
}