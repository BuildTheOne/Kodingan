const express = require("express");
const Currency = require("../controller/currencyController");
const router = express.Router();

router.get("/", Currency.getAllCurrencies);
router.post("/", Currency.calculateExchange)

module.exports = router;
