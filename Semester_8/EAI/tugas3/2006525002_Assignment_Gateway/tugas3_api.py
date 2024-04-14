from flask import Flask, redirect, request
import requests

app = Flask(__name__)

DOG_IMAGE_API_URL = 'https://dog.ceo/api/breeds/image/random'
RANDOM_JOKE_API_URL= 'https://official-joke-api.appspot.com/random_joke'
AGE_API_URL = 'https://api.agify.io'
GENDER_API_URL = 'https://api.genderize.io'
NATION_API_URL = 'https://api.nationalize.io'
ACTIVITY_API_URL = 'https://www.boredapi.com/api/activity'

@app.get("/")
def index():
    return redirect("/api")

@app.get("/api")
def home():
    return { "data": None,"message":"Success" }, 200

@app.get("/api/image")
def dog_image():
    response = requests.get(DOG_IMAGE_API_URL)
    json_response = response.json()
    result = json_response['message']
    return { "image":result }, 200

@app.get("/api/joke")
def random_joke():
    response = requests.get(RANDOM_JOKE_API_URL)
    json_response = response.json()
    setup = json_response['setup']
    punchline = json_response['punchline']
    return { "setup":setup, "punchline": punchline }, 200

@app.post("/api/age")
def age_guesser():
    name = request.get_json()['name']
    response = requests.get(f'{AGE_API_URL}?name={name}')
    json_response = response.json()
    result = json_response['age']
    if result == None: 
        result = "-"
    return { "age": result }, 200

@app.post("/api/gender")
def gender_guesser():
    gender = request.get_json()['name']
    response = requests.get(f'{GENDER_API_URL}?name={gender}')
    json_response = response.json()
    result = json_response['gender']
    if result == None: 
        result = "-"
    return { "gender": result }, 200

@app.post("/api/nation")
def nation_guesser():
    name = request.get_json()['name']
    response = requests.get(f'{NATION_API_URL}?name={name}')
    json_response = response.json()
    result = json_response['country'][0]['country_id']
    return { "nation": result }, 200

@app.get("/api/hobby")
def random_activity():
    response = requests.get(ACTIVITY_API_URL)
    json_response = response.json()
    result = json_response['activity']
    return { "hobby": result }, 200
