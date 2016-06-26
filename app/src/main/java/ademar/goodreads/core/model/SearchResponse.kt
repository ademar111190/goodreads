package ademar.goodreads.core.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "GoodreadsResponse", strict = false)
class SearchResponse {

    @field:Element(name = "search")
    lateinit var search: Search

}
