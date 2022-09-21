#!/usr/bin/env python

import socket
import time
from datetime import date
import hashlib
import random
import string

SERVER_IP = "0.0.0.0"
SERVER_PORT = 5992
BUFFER_SIZE = 1024

user_data = {'username': "ignatius-dimas", "password": "1234567890", "session_id": ""}


def logic(input_value: str):
    output_value = {}
    for char in input_value:
        if char in output_value.keys():
            output_value[char] += 1
        else:
            output_value[char] = 1
    return str(output_value)


def checkUsername(input_usn, input_pwd):
    if (user_data['username'] == input_usn) and (user_data['password'] == input_pwd):
        return True
    return False


def main():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sc:
        sc.bind((SERVER_IP, SERVER_PORT))
        sc.listen(0)

        print("~~ Welcome to Advanced Date Calculator Server ~~")
        print("~~    By Ignatius Henriyanto (2006525002)     ~~")
        print("Server is ready to receive")

        while True:
            connection, address = sc.accept()
            print(f"Receive connection from {address}")

            input_usn_bytes = connection.recv(BUFFER_SIZE)
            input_usn = input_usn_bytes.decode("UTF-8")
            input_pwd_bytes = connection.recv(BUFFER_SIZE)
            input_pwd = input_pwd_bytes.decode("UTF-8")

            if (checkUsername(input_usn, input_pwd)):
                # Send session_id
                session = ""
                for i in range(33):
                    chars = string.ascii_letters + "1234567890"
                    session += random.choice(chars)
                    user_data['session_id'] = session
                connection.send((session).encode("UTF-8"))
                print(f"Login successful from {address} by {input_usn}")

                active = True
                while active:
                    input_session = connection.recv(BUFFER_SIZE)
                    session_rcv = input_session.decode("UTF-8")

                    # Check if session valid
                    if (user_data["session_id"] == session_rcv):
                        connection.send(("Session valid").encode("UTF-8"))
                        # print("Session valid")

                        input_choice = connection.recv(BUFFER_SIZE)
                        choice = input_choice.decode("UTF-8")

                        print(choice)

                        if (choice == "1"):
                            connection.send(("1").encode("UTF-8"))
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
                        else:
                            print(f"{user_data['username']} Logout successfully")
                            user_data["session_id"] = ""
                            connection.send(("0").encode("UTF-8"))
                            connection.close()
                            active = False
                    else:
                        print("Session invalid")
                        connection.send("Session invalid".encode("UTF-8"))
            else:
                connection.send(("Session invalid").encode("UTF-8"))
                print(f"Login failed from {address}")
                connection.close()


if __name__ == "__main__":
    main()
