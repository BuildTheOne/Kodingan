import urllib.request, json, time

idList = ["317739", "318218", "318217", "318210", "318214", "317730", "317724", "318205", "317741", "317740", "318233", "318215", "318231", "317746", "318230", "317725", "318236", "318227", "318246", "317729", "318234", "318226", "317749", "317723", "317742", "317748", "318220", "317745", "318216", "317738", "317727", "318221", "318112", "318208", "318206", "317750", "318207", "318120", "318083", "318118", "317731", "318117", "318204", "318209", "318115", "318229", "318223", "318212", "317752", "318247", "318228", "318224", "318232", "319448", "318239", "318225", "318222", "318213", "318219", "318251", "317726", "318114"]

while True:
    m = 0
    for showroomId in idList:
        url = "https://www.showroom-live.com/api/room/profile?room_id=" + showroomId
        with urllib.request.urlopen(url) as url:
            data = json.loads(url.read().decode())
            if data["is_onlive"]:
                onLive = "is live!"
                m += 1
            else:
                # onLive = "is not live"
                continue
            print(data["main_name"] + onLive)
    print(f"\nAda {m} member yang lagi live.")
    time.sleep(900)