#!/usr/bin/env python

import socket
import time

SERVER_IP = "0.0.0.0"
SERVER_PORT = 5992
BUFFER_SIZE = 1024


def logic(input_value: str):
    output_value = {}
    for char in input_value:
        if char in output_value.keys():
            output_value[char] += 1
        else:
            output_value[char] = 1
    return str(output_value)


def main():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sc:
        sc.bind((SERVER_IP, SERVER_PORT))
        sc.listen(0)

        print("Example Socket Server Program")

        while True:
            connection, address = sc.accept()
            print(f"Receive connection from {address}")

            numbers_bytes = connection.recv(BUFFER_SIZE)
            numbers = numbers_bytes.decode("UTF-8")
            choice_bytes = connection.recv(1024)
            choice = choice_bytes.decode("UTF-8")

            nums = numbers.split()
            results = 0

            if choice == '1':
                results = int(nums[0]) * int(nums[1])
            elif choice == '2':
                results = int(nums[0]) / int(nums[1])
            elif choice == '3':
                results = int(nums[0]) + int(nums[1])
            elif choice == '4':
                results = int(nums[0]) - int(nums[1])

            modifiedMessage = str(results).encode("UTF-8")

            print('Numbers from client', numbers)

            print('Answer from Server', modifiedMessage)

            connection.send(modifiedMessage)
            connection.close()


if __name__ == "__main__":
    main()
