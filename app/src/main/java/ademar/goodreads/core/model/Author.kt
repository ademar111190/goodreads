package ademar.goodreads.core.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "author", strict = false)
class Author {

    @field:Element(name = "id")
    var id: Long = 0

    @field:Element(name = "name")
    lateinit var name: String

    override fun equals(other: Any?): Boolean {
        return other is Author && id == other.id
    }

    override fun hashCode(): Int {
        return id.toInt()
    }

}
