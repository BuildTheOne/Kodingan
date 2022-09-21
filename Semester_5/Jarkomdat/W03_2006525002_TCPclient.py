#!/usr/bin/env python

import socket
import time

SERVER_IP = "34.135.201.188"
SERVER_PORT = 5992
BUFFER_SIZE = 1024


def main():
    sc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sc.connect((SERVER_IP, SERVER_PORT))

    print("~~ Welcome to Date Calculator Program  ~~")
    print("~~ By Ignatius Henriyanto (2006525002) ~~")
    date_start = input("Input start date in the format dd/mm/yyyy: ")
    dateStart_bytes = date_start.encode("UTF-8")

    date_end = input("Input end date in the format dd/mm/yyyy: ")
    dateEnd_bytes = date_end.encode("UTF-8")

    sc.send(dateStart_bytes)
    sc.send(dateEnd_bytes)

    modifiedMessage_bytes = sc.recv(BUFFER_SIZE)
    modifiedMessage = modifiedMessage_bytes.decode("UTF-8")

    print('Answer From Server:', modifiedMessage + " days")

    sc.close()


if __name__ == "__main__":
    main()
