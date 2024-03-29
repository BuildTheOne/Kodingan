const express = require("express");
const Weather = require("../controller/weatherController");
const router = express.Router();

router.get("/", Weather.getWeather);

module.exports = router;
