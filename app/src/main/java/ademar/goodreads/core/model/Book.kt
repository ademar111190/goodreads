package ademar.goodreads.core.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "book", strict = false)
class Book {

    @field:Element(name = "id")
    var id: Long = 0

    @field:Element(name = "title")
    lateinit var title: String

    @field:Element(name = "image_url")
    lateinit var image: String

    @field:Element(name = "author")
    lateinit var author: Author

    override fun equals(other: Any?): Boolean {
        return other is Book && id == other.id
    }

    override fun hashCode(): Int {
        return id.toInt()
    }

}
