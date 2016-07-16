package ademar.goodreads.core.model

import ademar.goodreads.test.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.simpleframework.xml.core.Persister

class BookTest : BaseTestCase() {

    @Test
    fun testXmlDeserialize() {
        val serializer = Persister()
        val book = serializer.read(Book::class.java, XML_BEST_BOOK)

        assertThat(book).isNotNull()
        assertThat(book.id).isEqualTo(BEST_BOOK_ID)
        assertThat(book.image).isEqualTo(BEST_BOOK_IMAGE)
        assertThat(book.title).isEqualTo(BEST_BOOK_TITLE)
        assertThat(book.author).isNotNull()
    }

}
