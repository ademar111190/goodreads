package ademar.goodreads.test

import ademar.goodreads.App
import ademar.goodreads.core.injector.AppComponent
import ademar.goodreads.core.injector.AppMockModule
import ademar.goodreads.core.injector.DaggerAppMockComponent
import junit.framework.Assert
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = ademar.goodreads.BuildConfig::class, sdk = intArrayOf(SDK), packageName = "ademar.goodreads", shadows = arrayOf(AppShadowAsyncTask::class))
abstract class BaseTestCase {

    lateinit protected var mAppMockModule: AppMockModule
    private var mHasServiceReturn = false
    private val mSignal = CountDownLatch(1)

    @Before
    @Throws(Exception::class)
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
        App.instance = APP
        ShadowLog.stream = System.out
        mAppMockModule = AppMockModule()
        AppComponent.Initialize.set(DaggerAppMockComponent.builder().build())
    }

    @After
    @Throws(Exception::class)
    open fun tearDown() {
    }

    @Throws(Exception::class)
    protected fun wait(seconds: Int) {
        mSignal.await(seconds.toLong(), TimeUnit.SECONDS)
        assertThat(mHasServiceReturn).isTrue()
    }

    protected fun wake() {
        mHasServiceReturn = true
        mSignal.countDown()
    }

    protected fun sleep(ms: Int) {
        try {
            Thread.sleep(ms.toLong())
        } catch (e: InterruptedException) {
            Assert.fail(e.message)
        }
    }

}
