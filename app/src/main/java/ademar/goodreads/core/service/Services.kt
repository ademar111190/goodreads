package ademar.goodreads.core.service

import ademar.goodreads.R
import ademar.goodreads.core.ext.app
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

open class Services {

    val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(app().getString(R.string.api_url))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(client)
            .build()

    val searchService: SearchService = retrofit.create(SearchService::class.java)

}
