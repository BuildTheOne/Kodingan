import string
import re

def stopword(): #To define preposition in list
  stopword = open(r"Python\Code\DDP0\Stopword.txt", "r")
  stopword = stopword.read().split("\n")
  return stopword

def punc(): # To define list punctuation
  punc = string.punctuation
  punc = punc.replace("-", "")
  punc = r"[{}]".format(punc)
  return punc

def msg_list(): #To define message in list
  stopword_list = stopword()
  punct = punc()
  msg = message.lower()
  msg = re.sub(punct, "", msg)
  msg = msg.split(" ")
  msg_list = [i for i in msg if i not in stopword_list]
  if "-" in msg_list:
    msg_list.remove("-")
  return msg_list

print("=============================================================\n"
"Masukkan Pesan : (untuk berhenti masukkan string kosong)\n"
"=============================================================")

message = input()
print(msg_list())

# while True:
#   message = input()
#   print("Pesan : " + message)
#   msg = msg_list()
#   if message == "" :
    
#     break

# print(msg)