from flask import Flask, render_template, request
import requests
import sys

app = Flask(__name__, template_folder='')

BASE_API_URL = 'https://api.dictionaryapi.dev/api/v2/entries/en/'

@app.route("/",methods=['GET', 'POST'])
def search():
    definitions = []
    keyword = ''
    if request.method == 'POST':
        keyword, definitions= getDataMeanings(request.form['keyword'])
    return render_template('tampilan.html', data=definitions, keyword=keyword)

def getDataMeanings(keyword=None):
    api_url = f'{BASE_API_URL}{keyword}'
    response = requests.get(api_url)
    json_response = response.json()
    app.logger.info(json_response)
    return json_response[0]['word'], json_response[0]['meanings']