package ademar.goodreads.test

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

// region Database

val DATABASE_NAME = "goodreads"
val DATABASE_VERSION = 1

// endregion

// region Model

val AUTHOR_ID = 432L
val AUTHOR_NAME = "Ayn Rand"
val BEST_BOOK_ID = 662L
val BEST_BOOK_IMAGE = "https://d.gr-assets.com/books/1405868167m/662.jpg"
val BEST_BOOK_TITLE = "Atlas Shrugged"
val SEARCH_END = 1
val SEARCH_QUERY = "Atlas Shrugged"
val SEARCH_START = 1
val SEARCH_TOTAL = 1
val WORK_AVERAGE_RATING = 3.67
val WORK_BOOKS = 13
val WORK_ID = 817219L
val WORK_PUBLICATION_DAY = 31
val WORK_PUBLICATION_MONTH = 12
val WORK_PUBLICATION_YEAR = 1957
val WORK_RATINGS = 268028
val WORK_REVIEWS = 13514

val XML_AUTHOR = """<author>
    <id type="integer">$AUTHOR_ID</id>
    <name>$AUTHOR_NAME</name>
</author>"""

val XML_BEST_BOOK = """<best_book type="Book">
    <id type="integer">$BEST_BOOK_ID</id>
    <title>$BEST_BOOK_TITLE</title>
    $XML_AUTHOR
    <image_url>$BEST_BOOK_IMAGE</image_url>
</best_book>"""

val XML_WORK = """<work>
    <id type="integer">$WORK_ID</id>
    <books_count type="integer">$WORK_BOOKS</books_count>
    <ratings_count type="integer">$WORK_RATINGS</ratings_count>
    <text_reviews_count type="integer">$WORK_REVIEWS</text_reviews_count>
    <original_publication_year type="integer">$WORK_PUBLICATION_YEAR</original_publication_year>
    <original_publication_month type="integer">$WORK_PUBLICATION_MONTH</original_publication_month>
    <original_publication_day type="integer">$WORK_PUBLICATION_DAY</original_publication_day>
    <average_rating>$WORK_AVERAGE_RATING</average_rating>
    $XML_BEST_BOOK
</work>"""

val XML_SEARCH = """<search>
    <query><![CDATA[$SEARCH_QUERY]]></query>
    <results-start>$SEARCH_START</results-start>
    <results-end>$SEARCH_END</results-end>
    <total-results>$SEARCH_TOTAL</total-results>
    <results>
        $XML_WORK
    </results>
</search>"""

val XML_SEARCH_RESPONSE = """<?xml version="1.0" encoding="UTF-8"?>
<GoodreadsResponse>
  $XML_SEARCH
</GoodreadsResponse>"""

// endregion

fun buildRetrofit(server: MockWebServer): Retrofit {
    return Retrofit.Builder()
            .baseUrl(server.url(""))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build())
            .build()
}