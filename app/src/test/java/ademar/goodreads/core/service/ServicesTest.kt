package ademar.goodreads.core.service

import ademar.goodreads.test.BaseTestCase
import okhttp3.logging.HttpLoggingInterceptor
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class ServicesTest : BaseTestCase() {

    @Test
    fun testClient() {
        val client = Services().client
        assertThat(client).isNotNull()
        assertThat(client.interceptors()).isNotNull()
        assertThat(client.interceptors().size).isEqualTo(1)
        assertThat(client.interceptors()[0]).isNotNull()
        assertThat(client.interceptors()[0] is HttpLoggingInterceptor).isTrue()
    }

    @Test
    fun testRetrofit() {
        val retrofit = Services().retrofit
        assertThat(retrofit).isNotNull()
        assertThat(retrofit.baseUrl()).isNotNull()
        assertThat(retrofit.baseUrl().host()).isEqualTo("www.goodreads.com")
        assertThat(retrofit.baseUrl().isHttps).isTrue()

        assertThat(retrofit.callAdapterFactories()).isNotNull()
        assertThat(retrofit.callAdapterFactories().size).isGreaterThan(0)
        var correctClass = false
        retrofit.callAdapterFactories().forEach {
            assertThat(it).isNotNull()
            correctClass = correctClass || it is RxJavaCallAdapterFactory
        }
        assertThat(correctClass).isTrue()

        assertThat(retrofit.converterFactories()).isNotNull()
        assertThat(retrofit.converterFactories().size).isGreaterThan(0)
        correctClass = false
        retrofit.converterFactories().forEach {
            assertThat(it).isNotNull()
            correctClass = correctClass || it is SimpleXmlConverterFactory
        }
        assertThat(correctClass).isTrue()

        assertThat(retrofit.callFactory()).isNotNull()
    }

    @Test
    fun testSearchService() {
        val service = Services().searchService
        assertThat(service).isNotNull()
        assertThat(service is SearchService).isTrue()
    }

}
