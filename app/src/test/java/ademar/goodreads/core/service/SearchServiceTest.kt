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
    fun test() {
        assertThat(1).isEqualTo(1)
    }

    @Test
    fun testSearch() {
        server.enqueue(MockResponse().setBody(RESULT_SEARCH))
        val observable = service.search(SEARCH_QUERY)
        observable.subscribe { response ->
            assertThat(response.code()).isEqualTo(200)

            val body: SearchResponse = response.body()
            val search = body.search
            assertThat(search.query).isEqualToIgnoringCase(SEARCH_QUERY)
            assertThat(search.start).isEqualTo(SEARCH_RESULT_START)
            assertThat(search.end).isEqualTo(SEARCH_RESULT_END)
            assertThat(search.total).isEqualTo(SEARCH_RESULT_TOTAL)

            val works = search.works
            assertThat(works.size).isEqualTo(SEARCH_RESULT_END - (SEARCH_RESULT_START - 1))

            val work = works[0]
            assertThat(work.id).isEqualTo(SEARCH_WORK_ID)
            assertThat(work.booksCount).isEqualTo(SEARCH_WORK_BOOKS)
            assertThat(work.ratingsCount).isEqualTo(SEARCH_WORK_RATINGS)
            assertThat(work.reviewsCount).isEqualTo(SEARCH_WORK_REVIEWS)
            assertThat(work.publicationYear).isEqualTo(SEARCH_WORK_PUBLICATION_YEAR)
            assertThat(work.publicationMonth).isNull()
            assertThat(work.publicationDay).isNull()
            assertThat(work.averageRating).isEqualTo(SEARCH_WORK_AVERAGE_RATING)

            val book = work.bestBook
            assertThat(book.id).isEqualTo(SEARCH_WORK_BEST_BOOK_ID)
            assertThat(book.title).isEqualTo(SEARCH_WORK_BEST_BOOK_TITLE)
            assertThat(book.image).isEqualTo(SEARCH_WORK_BEST_BOOK_IMAGE)

            val author = book.author
            assertThat(author.id).isEqualTo(SEARCH_WORK_BEST_BOOK_AUTHOR_ID)
            assertThat(author.name).isEqualTo(SEARCH_WORK_BEST_BOOK_AUTHOR_NAME)
        }
    }

}
