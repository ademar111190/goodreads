package ademar.goodreads.core.injector

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppMockModule::class))
interface AppMockComponent : AppComponent
