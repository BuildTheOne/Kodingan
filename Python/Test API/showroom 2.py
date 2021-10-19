import urllib.request, json, time

url = "https://www.showroom-live.com/api/live/onlives"

with urllib.request.urlopen(url) as url:
    data = json.loads(url.read().decode())["onlives"][0]

dataLive = data["lives"]

# for i in dataLive:
#     name = i["main_name"]
#     if ("JKT48" in name):
#         urlLive = f"https://www.showroom-live.com/{i['room_url_key']}"
#         m += 1
#         print(f"{name}is live!")
#         # print(f"{name}")
# print(f"\nAda {m} member yang lagi live sekarang!")
lst = []
for i in dataLive:
    name = i["main_name"]
    if ("JKT48" in name):
        urlLive = f"https://www.showroom-live.com/{i['room_url_key']}"
        lst.append([name, urlLive])
print(f"Ada {len(lst)} member yang lagi live sekarang!\n")
for j in lst:
    # print(f"{j[0]}is live in {j[1]}")
    print(f"Ada {j[0]} yang lagi live di {j[1]}")

