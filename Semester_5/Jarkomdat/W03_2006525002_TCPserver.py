#!/usr/bin/env python

import socket
import time
from datetime import date

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

        print("~~ Welcome to Date Calculator Program  ~~")
        print("~~ By Ignatius Henriyanto (2006525002) ~~")

        while True:
            connection, address = sc.accept()
            print(f"Receive connection from {address}")

            input_dateEnd_bytes = connection.recv(BUFFER_SIZE)
            input_dateStart = input_dateEnd_bytes.decode("UTF-8")
            input_dateEnd_bytes = connection.recv(1024)
            input_dateEnd = input_dateEnd_bytes.decode("UTF-8")

            dateStartList = input_dateStart.split("/")
            dateEndList = input_dateEnd.split("/")

            dateStart = date(int(dateStartList[2]), int(dateStartList[1]), int(dateStartList[0]))
            dateEnd = date(int(dateEndList[2]), int(dateEndList[1]), int(dateEndList[0]))

            delta = dateEnd - dateStart

            modifiedMessage = str(delta.days).encode("UTF-8")

            print('Date from client:', input_dateStart + " to " + input_dateEnd)

            print('Answer from Server', modifiedMessage)

            connection.send(modifiedMessage)
            connection.close()


if __name__ == "__main__":
    main()
