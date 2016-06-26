package ademar.goodreads.test

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

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

// region SEARCH

val SEARCH_AUTHENTICATION = true
val SEARCH_KEY = "SdkABCDEFGHIJKLMNOPQRS"
val SEARCH_METHOD = "search_search"
val SEARCH_QUERY = "Atlas Shrugged"
val SEARCH_RESULT_END = 1
val SEARCH_RESULT_START = 1
val SEARCH_RESULT_TOTAL = 1
val SEARCH_SOURCE = "Goodreads"
val SEARCH_TIME = 0.2
val SEARCH_WORK_AVERAGE_RATING = 3.67
val SEARCH_WORK_BEST_BOOK_AUTHOR_ID = 432
val SEARCH_WORK_BEST_BOOK_AUTHOR_NAME = "Ayn Rand"
val SEARCH_WORK_BEST_BOOK_ID = 662
val SEARCH_WORK_BEST_BOOK_IMAGE = "https://d.gr-assets.com/books/1405868167m/662.jpg"
val SEARCH_WORK_BEST_BOOK_IMAGE_SMALL = "https://d.gr-assets.com/books/1405868167s/662.jpg"
val SEARCH_WORK_BEST_BOOK_TITLE = "Atlas Shrugged"
val SEARCH_WORK_BOOKS = 13
val SEARCH_WORK_ID = 817219
val SEARCH_WORK_PUBLICATION_YEAR = 1957
val SEARCH_WORK_RATINGS = 268028
val SEARCH_WORK_REVIEWS = 13514

val RESULT_SEARCH = """<?xml version="1.0" encoding="UTF-8"?>
<GoodreadsResponse>
  <Request>
    <authentication>$SEARCH_AUTHENTICATION</authentication>
    <key><![CDATA[$SEARCH_KEY]]></key>
    <method><![CDATA[$SEARCH_METHOD]]></method>
  </Request>
  <search>
    <query><![CDATA[$SEARCH_QUERY]]></query>
    <results-start>$SEARCH_RESULT_START</results-start>
    <results-end>$SEARCH_RESULT_END</results-end>
    <total-results>$SEARCH_RESULT_TOTAL</total-results>
    <source>$SEARCH_SOURCE</source>
    <query-time-seconds>$SEARCH_TIME</query-time-seconds>
    <results>
      <work>
        <id type="integer">$SEARCH_WORK_ID</id>
        <books_count type="integer">$SEARCH_WORK_BOOKS</books_count>
        <ratings_count type="integer">$SEARCH_WORK_RATINGS</ratings_count>
        <text_reviews_count type="integer">$SEARCH_WORK_REVIEWS</text_reviews_count>
        <original_publication_year type="integer">$SEARCH_WORK_PUBLICATION_YEAR</original_publication_year>
        <original_publication_month type="integer" nil="true"/>
        <original_publication_day type="integer" nil="true"/>
        <average_rating>$SEARCH_WORK_AVERAGE_RATING</average_rating>
        <best_book type="Book">
          <id type="integer">$SEARCH_WORK_BEST_BOOK_ID</id>
          <title>$SEARCH_WORK_BEST_BOOK_TITLE</title>
          <author>
            <id type="integer">$SEARCH_WORK_BEST_BOOK_AUTHOR_ID</id>
            <name>$SEARCH_WORK_BEST_BOOK_AUTHOR_NAME</name>
          </author>
          <image_url>$SEARCH_WORK_BEST_BOOK_IMAGE</image_url>
          <small_image_url>$SEARCH_WORK_BEST_BOOK_IMAGE_SMALL</small_image_url>
        </best_book>
      </work>
    </results>
  </search>
</GoodreadsResponse>"""

// endregion
