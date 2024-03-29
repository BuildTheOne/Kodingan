const WEATHER_QUAKE_API_URL = "https://cuaca-gempa-rest-api.vercel.app";
const WEATHER_API_URL = WEATHER_QUAKE_API_URL + "/weather"

const provinceList = [
  "aceh",
  "sumatera-utara",
  "sumatera-barat",
  "riau",
  "kepulauan-riau",
  "jambi",
  "sumatera-selatan",
  "bengkulu",
  "bangka-belitung",
  "lampung",
  "dki-jakarta",
  "jawa-barat",
  "jawa-tengah",
  "di-yogyakarta",
  "jawa-timur",
  "bali",
  "nusa-tenggara-timur",
  "nusa-tenggara-barat",
  "kalimantan-barat",
  "kalimantan-tengah",
  "kalimantan-utara",
  "kalimantan-timur",
  "kalimantan-selatan",
  "sulawesi-utara",
  "gorontalo",
  "sulawesi-tengah",
  "sulawesi-barat",
  "sulawesi-selatan",
  "sulawesi-tenggara",
  "maluku",
  "maluku-utara",
  "papua-barat",
  "papua",
  "",
];

const Weather = {
  getWeather: async (_, res) => {
    try {
      // TODO
      const data = { title: "Cuaca", provinceList }
      res.render('weather', data)
    } catch (error) {
      res.render("error/error", { message: error.message });
    }
  },
};

module.exports = Weather;
