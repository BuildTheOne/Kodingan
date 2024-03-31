const NEWS_API_URL = 'https://api-berita-indonesia.vercel.app'

const fetchNewsProvider = async () => {
  const data = await fetch(NEWS_API_URL)
  const result = await data.json()
  const endpoints = result.endpoints
  return endpoints
}

const fetchNewsCategories = async (url) => {
  const res = await fetch(url)
  const result = await res.json()
  const data = result.data.posts
  return data
}

const title = 'Berita Indonesia'

const News = {
  getAllNewsProvider: async (_, res) => {
    try {
      const newsProviders = await fetchNewsProvider()
      const newsProviderName = newsProviders[0].name

      const data = {
        title,
        newsProviders,
        newsProvider: newsProviderName
      }
      res.render("news", data);
    } catch (error) {
      res.render("error/error", { message: error.message });
    }
  },
  getNewsProvider: async (req, res) => {
    try {
      const { newsProvider } = req.body

      const newsProviders = await fetchNewsProvider()
      const newsProviderCategory = (newsProviders.find(n => n.name == newsProvider)).paths

      const data = {
        title,
        newsProviders,
        newsProviderCategory,
        newsProvider
      }
      res.render("news", data);
    } catch (error) {
      res.render("error/error", { message: error.message });
    }
  },
  getNewsCategory: async (req, res) => {
    try {
      const { newsProvider, newsCategory } = req.body

      const newsProviders = await fetchNewsProvider()
      const newsProviderCategory = (newsProviders.find(n => n.name == newsProvider)).paths
      const news = await fetchNewsCategories(newsCategory)

      const data = {
        title,
        newsProviders,
        newsProviderCategory,
        newsProvider,
        newsCategory,
        news
      }
      res.render("news", data);
    } catch (error) {
      res.render("error/error", { message: error.message });
    }
  }
}

module.exports = News