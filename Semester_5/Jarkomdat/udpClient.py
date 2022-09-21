#!/usr/bin/env python3

from socket import *


BUFFER_SIZE = 2048
SERVER_IP = '35.225.179.199'
SERVER_PORT = 5992

def main():
    with socket(AF_INET, SOCK_DGRAM) as clientSocket:
        print("~~ Welcome to Simple Calculation Program ~~")
        numbers = input("Input two numbers separated by whitespace: ")
        print("Select your operation")
        print("1. Multiplication")
        print("2. Division")
        print("3. Addition")
        print("4. Subtraction")
        choice = input("Your choice: ")

        clientSocket.sendto(numbers.encode(),(SERVER_IP, SERVER_PORT))
        clientSocket.sendto(choice.encode(), (SERVER_IP, SERVER_PORT))

        modifiedMessage, serverAddress = clientSocket.recvfrom(BUFFER_SIZE)

        print("Answer from server: ",modifiedMessage.decode())

    
if __name__ == "__main__":
    main()
