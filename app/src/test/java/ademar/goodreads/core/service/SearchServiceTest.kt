package ademar.goodreads.core.service

import ademar.goodreads.core.model.SearchResponse
import ademar.goodreads.test.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class SearchServiceTest : BaseTestCase() {

    private lateinit var server: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var service: SearchService

    @Before
    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        server = MockWebServer()
        server.start()
        retrofit = buildRetrofit(server)
        service = retrofit.create(SearchService::class.java)
    }

    @Test
    fun testSearch() {
        server.enqueue(MockResponse().setBody(XML_SEARCH_RESPONSE))
        val observable = service.search(SEARCH_QUERY)
        observable.subscribe { response ->
            assertThat(response.code()).isEqualTo(200)

            val body: SearchResponse = response.body()
            val search = body.search
            assertThat(search.query).isEqualToIgnoringCase(SEARCH_QUERY)
            assertThat(search.start).isEqualTo(SEARCH_START)
            assertThat(search.end).isEqualTo(SEARCH_END)
            assertThat(search.total).isEqualTo(SEARCH_TOTAL)

            val works = search.works
            assertThat(works.size).isEqualTo(SEARCH_END - (SEARCH_START - 1))

            val work = works[0]
            assertThat(work.id).isEqualTo(WORK_ID)
            assertThat(work.booksCount).isEqualTo(WORK_BOOKS)
            assertThat(work.ratingsCount).isEqualTo(WORK_RATINGS)
            assertThat(work.reviewsCount).isEqualTo(WORK_REVIEWS)
            assertThat(work.publicationDay).isEqualTo(WORK_PUBLICATION_DAY)
            assertThat(work.publicationMonth).isEqualTo(WORK_PUBLICATION_MONTH)
            assertThat(work.publicationYear).isEqualTo(WORK_PUBLICATION_YEAR)
            assertThat(work.averageRating).isEqualTo(WORK_AVERAGE_RATING)

            val book = work.bestBook
            assertThat(book.id).isEqualTo(BEST_BOOK_ID)
            assertThat(book.title).isEqualTo(BEST_BOOK_TITLE)
            assertThat(book.image).isEqualTo(BEST_BOOK_IMAGE)

            val author = book.author
            assertThat(author.id).isEqualTo(AUTHOR_ID)
            assertThat(author.name).isEqualTo(AUTHOR_NAME)
        }
    }

}
