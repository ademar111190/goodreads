package ademar.goodreads.core.ext

import ademar.goodreads.test.BaseTestCase
import org.junit.Test

class ViewTest : BaseTestCase() {

//    @Mock lateinit var view: View
//    @Mock lateinit var viewTreeObserver: ViewTreeObserver
//    @Mock lateinit var canvas: Canvas

    @Test
    fun testAtFirstGlobalLayout() {
        // TODO mock final class ViewTreeObserver
//        val listeners = ArrayList<ViewTreeObserver.OnGlobalLayoutListener>()
//
//        When(viewTreeObserver.addOnGlobalLayoutListener(any())).thenAnswer { invocation ->
//            listeners.add(invocation.arguments[0] as ViewTreeObserver.OnGlobalLayoutListener)
//        }
//        When(viewTreeObserver.removeOnGlobalLayoutListener(any())).thenAnswer { invocation ->
//            assertThat(listeners.remove(invocation.arguments[0])).isTrue()
//        }
//
//        When(view.viewTreeObserver).thenReturn(viewTreeObserver)
//
//        When(view.draw(any())).then {
//            listeners.forEach { listener ->
//                listener.onGlobalLayout()
//            }
//        }
//
//        var calledCount = 0
//        view.atFirstGlobalLayout {
//            calledCount++
//        }
//
//        view.draw(canvas)
//        view.draw(canvas)
//        view.draw(canvas)
//
//        assertThat(calledCount).isEqualTo(1)
//
//        verify(viewTreeObserver).addOnGlobalLayoutListener(any())
//        verify(viewTreeObserver).removeOnGlobalLayoutListener(any())
    }

}
