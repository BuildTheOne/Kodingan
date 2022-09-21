#!/usr/bin/env python

import socket
import time

SERVER_IP = "35.225.179.199"
SERVER_PORT = 5992
BUFFER_SIZE = 1024


def main():
    sc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sc.connect((SERVER_IP, SERVER_PORT))

    print("~~ Welcome to Simple Calculation Program ~~")
    numbers = input("Input two numbers separated by whitespace: ")
    numbers_bytes = numbers.encode("UTF-8")

    print("Select your operation")
    print("1. Multiplication")
    print("2. Division")
    print("3. Addition")
    print("4. Substraction")
    choice = input("Your choice: ")
    choice_bytes = choice.encode("UTF-8")

    sc.send(numbers_bytes)
    sc.send(choice_bytes)

    modifiedMessage_bytes = sc.recv(BUFFER_SIZE)
    modifiedMessage = modifiedMessage_bytes.decode("UTF-8")

    print('Answer From Server: ', modifiedMessage)

    sc.close()


if __name__ == "__main__":
    main()
