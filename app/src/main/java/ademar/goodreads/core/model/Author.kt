package ademar.goodreads.core.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "author", strict = false)
class Author {

    @field:Element(name = "id")
    var id: Int = 0

    @field:Element(name = "name")
    lateinit var name: String

}
