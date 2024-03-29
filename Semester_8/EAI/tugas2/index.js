const express = require('express')
const handlebars = require('express-handlebars');
const handlebarsHelper = require('handlebars-helpers');
const currencyRouter = require('./routes/currencyRoute')
const weatherRouter = require('./routes/weatherRoute')

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
    { title: "Cuaca dan Gempa", desc: "Informasi gempa terkini dan cuaca di Indonesia", link: "/weather" },
    { title: "Konvert Mata Uang", desc: "Konverter antar Mata Uang, termasuk Cryptocurrencies", link: "/currency" }
  ]
  res.render('home', { title: "Home", links: links })
})

app.use('/currency', currencyRouter)
app.use('/weather', weatherRouter)
