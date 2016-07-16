package ademar.goodreads.core.model

import ademar.goodreads.test.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.simpleframework.xml.core.Persister

class SearchTest : BaseTestCase() {

    @Test
    fun testXmlDeserialize() {
        val serializer = Persister()
        val search = serializer.read(Search::class.java, XML_SEARCH)

        assertThat(search).isNotNull()
        assertThat(search.end).isEqualTo(SEARCH_END)
        assertThat(search.query).isEqualTo(SEARCH_QUERY)
        assertThat(search.start).isEqualTo(SEARCH_START)
        assertThat(search.total).isEqualTo(SEARCH_TOTAL)
        assertThat(search.works).isNotNull()
        assertThat(search.works).isNotEmpty()
        assertThat(search.works.size).isEqualTo(1)
    }

}
