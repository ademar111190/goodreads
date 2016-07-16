package ademar.goodreads.core.database

import ademar.goodreads.test.BaseTestCase
import ademar.goodreads.test.DATABASE_NAME
import ademar.goodreads.test.DATABASE_VERSION
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AppDatabaseTest : BaseTestCase() {

    @Test
    fun testName() {
        assertThat(AppDatabase.NAME).isEqualTo(DATABASE_NAME)
    }

    @Test
    fun testVersion() {
        assertThat(AppDatabase.VERSION).isEqualTo(DATABASE_VERSION)
    }

}
