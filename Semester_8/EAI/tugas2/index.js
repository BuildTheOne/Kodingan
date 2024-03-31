const express = require('express')
const handlebars = require('express-handlebars');
const handlebarsHelper = require('handlebars-helpers');
const currencyRouter = require('./routes/currencyRoute')
const newsRouter = require('./routes/newsRoute')

require('dotenv').config()

const app = express()
const PORT = process.env.PORT || 3000

app.use(express.static('public'))
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

handlebarsHelper()
app.engine('hbs', handlebars.engine({
  layoutsDir: __dirname + "/views/layouts",
  defaultLayout: 'layout',
  extname: "hbs",
}));
app.set('view engine', 'hbs');

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`)
})

app.get('/', (_, res) => {
  const links = [
    { title: "Konvert Mata Uang", desc: "Konverter antar Mata Uang, termasuk Cryptocurrencies", link: "/currency" },
    { title: "Berita Indonesia", desc: "Berita-berita di Indonesia", link: "/news" }
  ]
  res.render('home', { title: "Home", links: links })
})

app.use('/currency', currencyRouter)
app.use('/news', newsRouter)
