#!/usr/bin/env python3

from socket import *

BUFFER_SIZE = 2048
SERVER_IP = '34.135.201.188'
SERVER_PORT = 5992

def main():
    with socket(AF_INET, SOCK_DGRAM) as clientSocket:
        print("~~ Welcome to Date Calculator Program  ~~")
        print("~~ By Ignatius Henriyanto (2006525002) ~~")
        date_start = input("Input start date in the format dd/mm/yyyy: ")
        date_end = input("Input end date in the format dd/mm/yyyy: ")

        clientSocket.sendto(date_start.encode(), (SERVER_IP, SERVER_PORT))
        clientSocket.sendto(date_end.encode(), (SERVER_IP, SERVER_PORT))

        modifiedMessage, serverAddress = clientSocket.recvfrom(BUFFER_SIZE)

        print("Answer from server:", modifiedMessage.decode()+" days")


if __name__ == "__main__":
    main()
