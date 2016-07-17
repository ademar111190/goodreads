package ademar.goodreads.core.injector

import ademar.goodreads.core.manager.SearchManager
import ademar.goodreads.core.service.Services
import dagger.Module
import dagger.Provides
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import javax.inject.Singleton

@Module
class AppMockModule {

    @Mock lateinit var searchManager: SearchManager
    @Mock lateinit var services: Services

    init {
        MockitoAnnotations.initMocks(this)
    }

    @Provides
    @Singleton
    fun provideSearchManager(): SearchManager {
        return searchManager
    }

    @Provides
    @Singleton
    fun provideServices(): Services {
        return services
    }

}
