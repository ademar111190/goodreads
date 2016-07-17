package ademar.goodreads.core.injector

import ademar.goodreads.core.manager.SearchManager
import ademar.goodreads.core.service.Services
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideSearchManager(): SearchManager {
        return SearchManager()
    }

    @Provides
    @Singleton
    fun provideServices(): Services {
        return Services()
    }

}
