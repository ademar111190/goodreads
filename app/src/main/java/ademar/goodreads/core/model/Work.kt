package ademar.goodreads.core.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "work", strict = false)
class Work {

    @field:Element(name = "id")
    var id: Int = 0

    @field:Element(name = "books_count")
    var booksCount: Int = 0

    @field:Element(name = "ratings_count")
    var ratingsCount: Int = 0

    @field:Element(name = "text_reviews_count")
    var reviewsCount: Int = 0

    @field:Element(name = "original_publication_year", required = false)
    var publicationYear: Int? = null

    @field:Element(name = "original_publication_month", required = false)
    var publicationMonth: Int? = null

    @field:Element(name = "original_publication_day", required = false)
    var publicationDay: Int? = null

    @field:Element(name = "average_rating")
    var averageRating: Double = 0.0

    @field:Element(name = "best_book")
    lateinit var bestBook: Book

}
