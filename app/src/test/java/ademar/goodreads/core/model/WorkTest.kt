package ademar.goodreads.core.model

import ademar.goodreads.test.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.simpleframework.xml.core.Persister

class WorkTest : BaseTestCase() {

    @Test
    fun testXmlDeserialize() {
        val serializer = Persister()
        val work = serializer.read(Work::class.java, XML_WORK)

        assertThat(work).isNotNull()
        assertThat(work.id).isEqualTo(WORK_ID)
        assertThat(work.booksCount).isEqualTo(WORK_BOOKS)
        assertThat(work.ratingsCount).isEqualTo(WORK_RATINGS)
        assertThat(work.reviewsCount).isEqualTo(WORK_REVIEWS)
        assertThat(work.publicationDay).isEqualTo(WORK_PUBLICATION_DAY)
        assertThat(work.publicationMonth).isEqualTo(WORK_PUBLICATION_MONTH)
        assertThat(work.publicationYear).isEqualTo(WORK_PUBLICATION_YEAR)
        assertThat(work.averageRating).isEqualTo(WORK_AVERAGE_RATING)
        assertThat(work.bestBook).isNotNull()
    }

}
