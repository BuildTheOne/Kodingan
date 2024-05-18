from flask import Flask, render_template, request
from bs4 import BeautifulSoup
import requests

BASE_URL = "https://books.toscrape.com"

app = Flask(__name__, template_folder="")


@app.route("/", methods=["GET", "POST"])
def search():
    title = ""
    category = ""
    category_data = generate_category()
    data = {}

    if request.method == "POST":
        title = request.form["title"]
        category = request.form["category"]
        data = generate_data(title, category)
    return render_template(
        "index.html",
        category_data=category_data,
        title=title,
        category=category,
        data=data,
    )


def generate_data(title, category):
    page1 = requests.get(f"{BASE_URL}/{category}")
    soup1 = BeautifulSoup(page1.content, "html.parser")

    books_list = soup1.find("ol", class_="row")
    result = []
    for book in books_list:
        if book.text.strip() == "":
            continue
        data = {}

        data["title"] = book.find("h3").a["title"]

        if title != "":
            if data["title"].lower().find(title.lower()) <= 0:
                continue

        data["image"] = "https://books.toscrape.com/" + book.find("img")["src"].replace(
            "../../../..", ""
        )
        data["price"] = book.find("p", class_="price_color").text.strip()
        data["rating"] = word_to_number(
            book.find("p", class_="star-rating").get("class", [])[1]
        )
        data["availibility"] = book.find(
            "p", class_="instock availability"
        ).text.strip()
        result.append(data)

    btn_next = soup1.find("li", class_="next")

    if btn_next != None:
        page2 = requests.get(
            f"{BASE_URL}/{category}".replace("index.html", btn_next.a["href"]).replace(
                "page-1.html", btn_next.a["href"]
            )
        )
        soup2 = BeautifulSoup(page2.content, "html.parser")

        books_list_2 = soup2.find("ol", class_="row")
        for book in books_list_2:
            if book.text.strip() == "":
                continue
            data = {}

            data["title"] = book.find("h3").a["title"]

            if title != "":
                if data["title"].lower().find(title.lower()) <= 0:
                    continue

            data["image"] = "https://books.toscrape.com/" + book.find("img")[
                "src"
            ].replace("../../../..", "")
            data["price"] = book.find("p", class_="price_color").text.strip()
            data["rating"] = word_to_number(
                book.find("p", class_="star-rating").get("class", [])[1]
            )
            data["availibility"] = book.find(
                "p", class_="instock availability"
            ).text.strip()
            result.append(data)

    btn_next_2 = soup2.find("li", class_="next")

    if btn_next_2 != None:
        page3 = requests.get(
            f"{BASE_URL}/{category}".replace("index.html", btn_next_2.a["href"])
            .replace("page-2.html", btn_next_2.a["href"])
            .replace("page-1.html", btn_next_2.a["href"])
        )
        soup3 = BeautifulSoup(page3.content, "html.parser")

        books_list_3 = soup3.find("ol", class_="row")
        for book in books_list_3:
            if book.text.strip() == "":
                continue
            data = {}

            data["title"] = book.find("h3").a["title"]

            if title != "":
                if data["title"].lower().find(title.lower()) <= 0:
                    continue

            data["image"] = "https://books.toscrape.com/" + book.find("img")[
                "src"
            ].replace("../../../..", "")
            data["price"] = book.find("p", class_="price_color").text.strip()
            data["rating"] = word_to_number(
                book.find("p", class_="star-rating").get("class", [])[1]
            )
            data["availibility"] = book.find(
                "p", class_="instock availability"
            ).text.strip()
            result.append(data)

    result.sort(key=lambda x: x["rating"], reverse=True)

    return result


def generate_category():
    page = requests.get(BASE_URL)
    soup = BeautifulSoup(page.content, "html.parser")

    data = [{"name": "Semua", "value": "catalogue/page-1.html"}]
    category_div = soup.find("div", class_="side_categories")
    categories = category_div.ul.li.ul
    for category in categories:
        if category.text.strip() == "":
            continue
        if category.text.strip() == "Erotica":
            continue
        data.append({"name": category.text.strip(), "value": category.a["href"]})

    return data


def word_to_number(word):
    number_mapping = {
        "one": 1,
        "two": 2,
        "three": 3,
        "four": 4,
        "five": 5,
    }
    return number_mapping.get(word.lower())
