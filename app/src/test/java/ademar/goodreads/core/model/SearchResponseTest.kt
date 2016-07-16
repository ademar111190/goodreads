package ademar.goodreads.core.model

import ademar.goodreads.test.BaseTestCase
import ademar.goodreads.test.XML_SEARCH_RESPONSE
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.simpleframework.xml.core.Persister

class SearchResponseTest : BaseTestCase() {

    @Test
    fun testXmlDeserialize() {
        val serializer = Persister()
        val searchResponse = serializer.read(SearchResponse::class.java, XML_SEARCH_RESPONSE)

        assertThat(searchResponse).isNotNull()
        assertThat(searchResponse.search).isNotNull()
    }

}
