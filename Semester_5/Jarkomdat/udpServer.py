#!/usr/bin/env python3

from socket import *


BUFFER_SIZE = 2048

SERVER_PORT = 5992


def operation():
    with socket(AF_INET, SOCK_DGRAM) as serverSocket:
        serverSocket.bind(('', SERVER_PORT))
        while 1:
        
            numbers, clientAddress = serverSocket.recvfrom(BUFFER_SIZE)
            inputnumber, clientAddress = serverSocket.recvfrom(BUFFER_SIZE)
            
            nums = numbers.split()
            results = 0


            choice = inputnumber.decode('UTF-8')

            if choice == "1":
                results = int(nums[0]) * int(nums[1])
            elif choice == "2":
                results = int(nums[0]) / int(nums[1])
            elif choice == "3":
                results = int(nums[0]) + int(nums[1])
            elif choice == "4":
                results = int(nums[0]) - int(nums[1])

            modifiedMessage = str(results).encode()

            print('Numbers from client', numbers)

            print('Answer from Server', modifiedMessage)

            serverSocket.sendto(modifiedMessage, clientAddress)





def main():
    print('The server is ready to receive')
    operation()
    
if __name__ == "__main__":
    main()