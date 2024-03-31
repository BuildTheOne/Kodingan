const express = require("express");
const News = require("../controller/newsController");
const router = express.Router();

router.get("/", News.getAllNewsProvider);
router.post("/provider", News.getNewsProvider)
router.post("/category", News.getNewsCategory)

module.exports = router;
