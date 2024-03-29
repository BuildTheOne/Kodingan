const CURRENCY_API_URL = "https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@";

const fetchCurrencyList = async () => {
  const data = await fetch(CURRENCY_API_URL + "latest/v1/currencies.json");
  const result = JSON.parse(await data.text());
  const currencyList = Object.keys(result).map((c) => ({
    currencyName: result[c],
    code: c,
  }));
  return currencyList;
};

const fetchCurrencyConverter = async (code1, code2, value, date = "latest") => {
  const data = await fetch(CURRENCY_API_URL + date + `/v1/currencies/${code1}.json`);
  const result = JSON.parse(await data.text())
  const currency = result[code1][code2]
  const convertedValue = currency * value
  return convertedValue
}

const Currency = {
  getAllCurrencies: async (_, res) => {
    try {
      const currencyList = await fetchCurrencyList();

      const currencyCode1 = "idr"
      const currencyCode2 = "usd"
      const currencyAmount1 = 1
      const currencyAmount2 = await fetchCurrencyConverter(currencyCode1, currencyCode2, currencyAmount1)

      const data = { title: "Konvert Mata Uang", currencies: currencyList, currencyAmount1, currencyAmount2, currencyCode1, currencyCode2 }
      res.render("currency", data);
    } catch (error) {
      res.render("error/error", { message: error.message });
    }
  },
  calculateExchange: async (req, res) => {
    try {
      const { currencyAmount1, currencyCode1, currencyCode2 } = req.body;

      const currencyList = await fetchCurrencyList();
      const currencyAmount2 = await fetchCurrencyConverter(currencyCode1, currencyCode2, currencyAmount1)

      const data = { title: "Konvert Mata Uang", currencies: currencyList, currencyAmount1, currencyAmount2, currencyCode1, currencyCode2 }

      res.render("currency", data);
    } catch (error) {
      res.render("error/error", { message: error.message });
    }
  },
  getAllCurrenciesDate: async (_, res) => {
    try {
      // TODO
    } catch (error) {
      res.render("error/error", { message: error.message });
    }
  },
  calculateExchangeDate: async (req, res) => {
    try {
      // TODO
    } catch (error) {
      res.render("error/error", { message: error.message });
    }
  }
};

module.exports = Currency;
