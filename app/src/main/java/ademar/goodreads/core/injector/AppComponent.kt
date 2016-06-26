package ademar.goodreads.core.injector

import ademar.goodreads.ui.activity.StartActivity
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(activity: StartActivity)

    object Initialize {

        private var sInstance: AppComponent? = null

        fun get(): AppComponent {
            if (sInstance == null) {
                set(DaggerAppComponent.create())
            }
            return sInstance!!
        }

        fun set(component: AppComponent) {
            sInstance = component
        }

    }

}
