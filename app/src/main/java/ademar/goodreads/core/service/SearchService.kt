package ademar.goodreads.core.service

import ademar.goodreads.R
import ademar.goodreads.core.ext.app
import ademar.goodreads.core.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface SearchService {

    @GET("search.xml")
    fun search(
            @Query("q") query: CharSequence,
            @Query("page") page: Int = 1,
            @Query("search[field]") field: SearchField = SearchField.ALL,
            @Query("key") key: String = app().getString(R.string.goodreads_key)
    ): Observable<Response<SearchResponse>>

    enum class SearchField(val title: String) {

        TITLE("title"),
        AUTHOR("author"),
        ALL("all");

        override fun toString(): String {
            return title
        }

    }

}
