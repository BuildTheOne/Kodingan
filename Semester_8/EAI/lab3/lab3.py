from flask import Flask, render_template, request
import requests

app = Flask(__name__)

DICT_API_URL = 'https://api.dictionaryapi.dev/api/v2/entries/en'
FRUIT_API_URL = 'https://www.fruityvice.com/api/fruit'

def getDropdownBuah()->list:
    result = []
    api_url = f'{FRUIT_API_URL}/all'
    response = requests.get(api_url)
    fruits = response.json()
    for fruit in fruits:
        result.append(fruit['name'])

    return result

def getDataMeanings(keyword=None):
    api_url = f'{DICT_API_URL}/{keyword}'
    response = requests.get(api_url)
    json_response = response.json()

    return json_response[0]['word'],json_response[0]['meanings']

def getFruitDetail(fruit):
    api_url = f'{FRUIT_API_URL}/{fruit}'
    response = requests.get(api_url)
    fruits_detail = response.json()

    return fruits_detail

@app.get('/api/dropdown')
def dropdownApi():
    dropdown = getDropdownBuah()
    return { "data": dropdown,"message":"Success" }, 200

@app.post('/api/fruit')
def getBuahDetail():
    req = request.get_json()
    if req['fruit']:
        keyword,definitions = getDataMeanings(req['fruit'])
        detail_fruit = getFruitDetail(req['fruit'])
        return { 
                "data": { 
                    "keyword":keyword, 
                    "definitions":definitions,
                    "detail":detail_fruit, 
                    },
                "message":"Success" 
                }, 200
    else:
        return { "data":None, "message":"Bad Request" },400
    
