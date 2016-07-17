package ademar.goodreads.core.ext

import ademar.goodreads.test.BaseTestCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mock
import retrofit2.Response
import rx.Observable

class ObservableTest : BaseTestCase() {

    @Mock lateinit var obsevable: Observable<Response<Any>>

    @Test
    fun testObservatory() {
        val observatory = obsevable.observatory()
        assertThat(observatory).isNotNull()
        assertThat(observatory.observer).isEqualTo(obsevable)
    }

}
