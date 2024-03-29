const express = require("express");
const Currency = require("../controller/currencyController");
const router = express.Router();

router.get("/", Currency.getAllCurrencies);
router.post("/", Currency.calculateExchange)
router.get("/by-date", Currency.getAllCurrenciesDate);
router.post("/by-date", Currency.calculateExchangeDate)

module.exports = router;
