package ademar.goodreads.core.model

import ademar.goodreads.test.AUTHOR_ID
import ademar.goodreads.test.AUTHOR_NAME
import ademar.goodreads.test.BaseTestCase
import ademar.goodreads.test.XML_AUTHOR
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.simpleframework.xml.core.Persister

class AuthorTest : BaseTestCase() {

    @Test
    fun testXmlDeserialize() {
        val serializer = Persister()
        val author = serializer.read(Author::class.java, XML_AUTHOR)

        assertThat(author).isNotNull()
        assertThat(author.id).isEqualTo(AUTHOR_ID)
        assertThat(author.name).isEqualTo(AUTHOR_NAME)
    }

}
