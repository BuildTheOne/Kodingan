from flask import Flask, render_template, request
import requests

app = Flask(__name__, template_folder='')

roles = ['guest', 'friend', 'inspector']

API_URL = 'http://127.0.0.1:4000/api'

@app.route("/", methods=['GET', 'POST'])
def search():
    name = ''
    role = ''
    data = {}
    if request.method == 'POST':
        name = request.form['name']
        role = request.form['role']
        data = getData(name, role)
    return render_template('index.html', roles=roles, data=data, name=name, role=role)

def getData(name, role):
    data = {}
    data['nama'] = name
    data['image'] = getImage()
    if (role == 'guest'):
        data['age'] = getAge(name)
        data['gender'] = getGender(name)
        data['nation'] = getNation(name)
    if (role == 'friend'):
        data['quote'] = getQuote()
        data['hobby'] = getHobby()
    if (role == 'inspector'):
        data['age'] = getAge(name)
        data['gender'] = getGender(name)
        data['nation'] = getNation(name)
        data['quote'] = getQuote()
        data['hobby'] = getHobby()
    return data

def getImage():
    response = requests.get(f'{API_URL}/image')
    json_response = response.json()
    return json_response['image']

def getQuote():
    response = requests.get(f'{API_URL}/joke')
    json_response = response.json()
    return json_response

def getAge(name):
    response = requests.post(f'{API_URL}/age', json={ "name": name })
    json_response = response.json()
    return json_response['age']

def getGender(name):
    response = requests.post(f'{API_URL}/gender', json={ "name": name })
    json_response = response.json()
    return json_response['gender']

def getNation(name):
    response = requests.post(f'{API_URL}/nation', json={ "name": name })
    json_response = response.json()
    return json_response['nation']

def getHobby():
    response = requests.get(f'{API_URL}/hobby')
    json_response = response.json()
    return json_response['hobby']
