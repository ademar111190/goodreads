package ademar.goodreads.core.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "search", strict = false)
class Search {

    @field:Element(name = "query")
    lateinit var query: String

    @field:Element(name = "results-start")
    var start: Int = 0

    @field:Element(name = "results-end")
    var end: Int = 0

    @field:Element(name = "total-results")
    var total: Int = 0

    @field:ElementList(name = "results")
    lateinit var works: List<Work>

}
