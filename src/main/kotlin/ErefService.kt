import model.ExampleItem
import model.NewsItem
import model.ResultItem

class ErefService
{
    val httpService = HttpService()

    private val EBOARD_NEWS_URL = "https://eref.vts.su.ac.rs/sr/default/eboard/news/noauth/1"
    private val EBOARD_EXAMPLES_URL = "https://eref.vts.su.ac.rs/sr/default/eboard/examples/noauth/1"
    private val EBOARD_RESULTS_URL = "https://eref.vts.su.ac.rs/sr/default/eboard/results/noauth/1"

    fun getNews(): List<NewsItem>
    {
        val document = httpService.fetchDocument(EBOARD_NEWS_URL)
        val newsItems = mutableListOf<NewsItem>()

        for (post in document.select(".eboard-post"))
        {
            val author = post.select(".professor-f").text().trim()
            val subject = post.select(".subjects-f").text().trim()
            val title = post.select(".eboard-post-title").text().trim()
            val body = post.select(".eboard-post-content").text().trim()

            newsItems.add(NewsItem(author, subject, title, body))
        }

        return newsItems
    }

    fun getExamples(): List<ExampleItem>
    {
        val document = httpService.fetchDocument(EBOARD_EXAMPLES_URL)
        val exampleItems = mutableListOf<ExampleItem>()

        for (post in document.select(".eboard-post"))
        {
            val author = post.select(".professor-f").text().trim()
            val subject = post.select(".subjects-f").text().trim()
            val title = "Kliše za: $subject"
            val body = post.select(".eboard-post-content").text().trim()

            exampleItems.add(ExampleItem(author, subject, body))
        }

        return exampleItems
    }

    fun getResults(): List<ResultItem>
    {
        val document = httpService.fetchDocument(EBOARD_RESULTS_URL)
        val resultItems = mutableListOf<ResultItem>()

        for (post in document.select(".eboard-post"))
        {
            val author = post.select(".professor-f").text().trim()
            val subject = post.select(".subjects-f").text().trim()
            val title = post.select(".eboard-post-title").text().trim()
            val body = post.select(".eboard-post-content").text().trim()

            resultItems.add(ResultItem(author, subject, title, body))
        }

        return resultItems
    }
}