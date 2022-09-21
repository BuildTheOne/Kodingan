#!/usr/bin/env python

import socket
import time

SERVER_IP = "34.135.201.188"
SERVER_PORT = 5992
BUFFER_SIZE = 1024

session_id = ""

def main():
    sc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sc.connect((SERVER_IP, SERVER_PORT))

    print("~~ Welcome to Date Calculator Program  ~~")
    print("~~ By Ignatius Henriyanto (2006525002) ~~")

    usn = input("Username: ")
    usn_bytes = usn.encode("UTF-8")
    pwd = input("Password: ")
    pwd_bytes = pwd.encode("UTF-8")

    sc.send(usn_bytes)
    sc.send(pwd_bytes)

    res_bytes = sc.recv(BUFFER_SIZE)
    res = res_bytes.decode("UTF-8")

    if (res):
        print(f"Login successful as {usn}")
        session_id = res

        active = True
        while active:
            sc.send(session_id.encode("UTF-8"))

            res_session_bytes = sc.recv(BUFFER_SIZE)
            res_session = res_session_bytes.decode("UTF-8")

            # print(res_session)

            if (res_session == "Session invalid"):
                print("Session invalid")
                active = False
                sc.close()
            else:
                print("Command: \n1 for calculate\n2 for Logout")
                choice = input("Your choice: ")
                sc.send(choice.encode("UTF-8"))

                res_cmd_bytes = sc.recv(BUFFER_SIZE)
                res_cmd = res_cmd_bytes.decode("UTF-8")

                if (res_cmd == "0"):
                    active = False
                    print("Logout sucsessfully")
                    sc.close()
                else:
                    date_start = input("Input start date in the format dd/mm/yyyy: ")
                    dateStart_bytes = date_start.encode("UTF-8")

                    date_end = input("Input end date in the format dd/mm/yyyy: ")
                    dateEnd_bytes = date_end.encode("UTF-8")

                    sc.send(dateStart_bytes)
                    sc.send(dateEnd_bytes)

                    modifiedMessage_bytes = sc.recv(BUFFER_SIZE)
                    modifiedMessage = modifiedMessage_bytes.decode("UTF-8")

                    print('Answer From Server:', modifiedMessage + " days")
    else:
        print("Login failed")
        sc.close()
    
if __name__ == "__main__":
    main()
