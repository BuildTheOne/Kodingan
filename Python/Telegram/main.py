from telegram.ext import Updater, CommandHandler
import requests
import re
import urllib.request as request
import json

token = '1730224167:AAErLA37mGpBJtweev2zGTcLbfriq5ShPBc'

url = "https://www.showroom-live.com/api/room/profile?room_id=317739"
with request.urlopen(url) as url:
    data = json.loads(url.read().decode())

def lists(bot, update):
    chat_id = update.message.chat_id
    bot.sendMessage(chat_id=chat_id, text=data["room_name"])

def main():
    updater = Updater(token)
    dp = updater.dispatcher
    dp.add_handler(CommandHandler('lists',lists))
    updater.start_polling()
    updater.idle()