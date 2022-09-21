#!/usr/bin/env python3

from socket import *
from datetime import date

BUFFER_SIZE = 2048
SERVER_PORT = 5992

def operation():
    with socket(AF_INET, SOCK_DGRAM) as serverSocket:
        serverSocket.bind(('', SERVER_PORT))
        while 1:
        
            input_dateStart, clientAddress = serverSocket.recvfrom(BUFFER_SIZE)
            input_dateEnd, clientAddress = serverSocket.recvfrom(BUFFER_SIZE)
            
            dateStartList = input_dateStart.decode().split("/")
            dateEndList = input_dateEnd.decode().split("/")

            dateStart = date(int(dateStartList[2]), int(dateStartList[1]), int(dateStartList[0]))
            dateEnd = date(int(dateEndList[2]), int(dateEndList[1]), int(dateEndList[0]))

            delta = dateEnd - dateStart

            modifiedMessage = str(delta.days).encode()

            print('Date from client:', input_dateStart.decode() + " to " + input_dateEnd.decode())

            print('Answer from Server:', modifiedMessage)

            serverSocket.sendto(modifiedMessage, clientAddress)





def main():
    print('The server is ready to receive')
    operation()
    
if __name__ == "__main__":
    main()