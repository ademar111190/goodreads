package ademar.goodreads.core.ext

import ademar.goodreads.test.*
import android.app.Activity
import android.content.res.Resources
import android.view.View
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Matchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.verify

class ActivityTest : BaseTestCase() {

    @Mock lateinit var activity: Activity
    @Mock lateinit var resources: Resources
    @Mock lateinit var view: View

    @Test
    fun testBindDimen() {
        When(activity.resources).thenReturn(resources)
        When(resources.getDimension(anyInt())).thenReturn(ACTIVITY_DIMEN_VALUE)

        val dimen = BindDimen(ACTIVITY_DIMEN_ID).getValue(activity, ActivityTest::activity)
        assertThat(dimen).isEqualTo(ACTIVITY_DIMEN_VALUE)

        verify(activity).resources
        verify(resources).getDimension(ACTIVITY_DIMEN_ID)
    }

    @Test
    fun testBindView() {
        When(activity.findViewById(anyInt())).thenReturn(view)

        val bindView = BindView<View>(ACTIVITY_VIEW_ID).getValue(activity, ActivityTest::activity)
        assertThat(bindView).isEqualTo(view)

        verify(activity).findViewById(ACTIVITY_VIEW_ID)
    }

}
