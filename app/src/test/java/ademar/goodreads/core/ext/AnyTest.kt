package ademar.goodreads.core.ext

import ademar.goodreads.test.APP
import ademar.goodreads.test.BaseTestCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AnyTest : BaseTestCase() {

    @Test
    fun testApp() {
        assertThat(app()).isNotNull()
        assertThat(app()).isEqualTo(APP)
    }

}
