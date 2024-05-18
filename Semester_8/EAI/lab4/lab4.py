from bs4 import BeautifulSoup
import requests
import csv

BASE_URL = 'https://quotes.toscrape.com'


def generate_data() -> list:
    page = requests.get(BASE_URL)
    soup = BeautifulSoup(page.content, 'html.parser')

    quotes = soup.find_all('div', class_="quote")

    result = []
    for quote in quotes:
        quote_result = {'quote': "", "author": "", "tags": ""}
        quote_result['quote'] = quote.span.string
        quote_result['author'] = quote.small.string

        all_a = quote.find_all('a', attrs={"class": "tag"})
        str_all_a = ''
        for a in all_a:
            str_all_a += a.string + ", "
        quote_result['tags'] = str_all_a

        result.append(quote_result)

    print(result)
    return result


def generate_output(data):
    with open('result.csv', mode='w', newline="") as result_csv:
        writer = csv.writer(result_csv, delimiter=";")
        writer.writerow(["No.", "Quote", "Author", "Tags"])

        num = 1
        for item in data:
            no = num
            num += 1
            quote = item['quote']
            author = item['author']
            tags = item['tags']
            writer.writerow([no, quote, author, tags])


def main():
    result = generate_data()
    generate_output(result)


if __name__ == '__main__':
    main()
