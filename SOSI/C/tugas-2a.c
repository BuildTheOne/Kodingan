/*
 * Nama   : Ignatius Henriyanto Primai Renda
 * NPM    : 2006525002
 * Kelas  : C
 */

#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

int main()
{
  int rPID, rPPID, id1000 = getpid(), offset = 1001;
  pid_t pid1 = fork();

  rPID = getpid() - id1000 + offset;
  rPPID = getppid() - id1000 + offset;

  // wait(NULL);
  if (pid1 == 0)
  {
    pid_t pid2 = fork();
    rPID = getpid() - id1000 + offset;
    rPPID = getppid() - id1000 + offset;
    if (pid2 == 0)
    {
      pid_t pid4 = fork();
      rPID = getpid() - id1000 + offset;
      rPPID = getppid() - id1000 + offset;
      // wait(NULL);
      if (pid4 == 0)
      {
        printf("PID[%d] \t PPID[%4d]\n", rPID, rPPID);
      }
      else
      {
        // Print 1004
        printf("PID[%d] \t PPID[%4d]\n", rPID, rPPID);
      }
    }
    else
    {
      pid_t pid5 = fork();
      rPID = getpid() - id1000 + offset;
      rPPID = getppid() - id1000 + offset;
      wait(NULL);
      if (pid5 == 0)
      {
        // Print 1005
        printf("PID[%d] \t PPID[%4d]\n", rPID, rPPID);
      }
      else
      {
        // Print 1002
        printf("PID[%d] \t PPID[%4d]\n", rPID, rPPID);
      }
    }
  }
  else
  {
    pid_t pid3 = fork();
    rPID = getpid() - id1000 + offset;
    rPPID = getppid() - id1000 + offset;
    wait(NULL);
    if (pid3 == 0)
    {
      // Print 1003
      printf("PID[%d] \t PPID[%4d]\n", rPID, rPPID);
    }
    else
    {
      // Print 1001
      printf("PID[%d] \t PPID[%4d]\n", rPID, rPPID);
    }
  }
  fflush(NULL);
  // return 0;
}
