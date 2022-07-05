package ademar.goodreads.ui.search.activity

import ademar.goodreads.R
import ademar.goodreads.core.ext.BindDimen
import ademar.goodreads.core.ext.BindView
import ademar.goodreads.core.ext.app
import ademar.goodreads.core.ext.onTextChanged
import ademar.goodreads.core.manager.SearchManager
import ademar.goodreads.ui.common.activity.BaseActivity
import ademar.goodreads.ui.search.adapter.SearchAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView

class SearchActivity : BaseActivity() {

    val VOICE_SEARCH = 1

    val searchField: EditText by BindView(R.id.search_field)
    val list: RecyclerView by BindView(R.id.list)
    val default: FrameLayout by BindView(R.id.default_view)
    val reload: TextView by BindView(R.id.reload)
    val load: ProgressBar by BindView(R.id.load)
    val empty: TextView by BindView(R.id.empty)
    val itemWidth: Float by BindDimen(R.dimen.work_item_size)

    val searchManager = SearchManager()
    lateinit var searchAdapter: SearchAdapter

    var hasVoiceRecognizer = false
    lateinit var inputMethod: InputMethodManager
    lateinit var recognizerIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)

        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.search_default))
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        hasVoiceRecognizer = packageManager.queryIntentActivities(recognizerIntent, 0).size > 0
        inputMethod = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_back_dark)
        toolbar.setNavigationOnClickListener {
            navigateUp(intent)
        }

        searchAdapter = SearchAdapter(searchManager)
        val layoutManager =
            StaggeredGridLayoutManager(
                1,
                StaggeredGridLayoutManager.VERTICAL
            )
        list.layoutManager = layoutManager
        list.adapter = searchAdapter
        list.viewTreeObserver.addOnGlobalLayoutListener {
            layoutManager.spanCount = Math.max(1, Math.floor(list.width.toDouble() / itemWidth.toDouble()).toInt())
        }

        searchField.onTextChanged { query ->
            showLoading()
            search(query)
        }

        reload.setOnClickListener {
            showLoading()
            search(searchField.text)
        }

        default.setOnClickListener { }

        showContentStart()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VOICE_SEARCH && resultCode == RESULT_OK) {
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (results != null && !results.isEmpty()) {
                val query = results[0]
                searchField.append(query)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (menu != null) {
            val hasSearchQuery = searchField.text.length > 0
            menu.findItem(R.id.clear).isVisible = hasSearchQuery
            menu.findItem(R.id.mic).isVisible = !hasSearchQuery && hasVoiceRecognizer
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.clear -> {
                searchField.text = null
                true
            }
            R.id.mic -> {
                if (hasVoiceRecognizer) {
                    startActivityForResult(recognizerIntent, VOICE_SEARCH)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun search(query: CharSequence?) {
        if (query == null || query.length == 0) {
            searchManager.clear()
            showContentStart()
        } else {
            showLoading()
            searchManager.search(query.toString()) { success ->
                if (success) {
                    showContent()
                } else {
                    showReload()
                }
            }
        }
    }

    fun showContentStart() {
        switchVisibleView(default)
    }

    fun showLoading() {
        switchVisibleView(load)
    }

    fun showReload() {
        switchVisibleView(reload)
    }

    fun showEmpty() {
        switchVisibleView(empty)
    }

    fun showContent() {
        if (searchAdapter.itemCount == 0) {
            showEmpty()
        } else {
            switchVisibleView(list)
        }
    }

    fun switchVisibleView(view: View) {
        invalidateOptionsMenu()
        load.visibility = if (view == load) View.VISIBLE else View.GONE
        reload.visibility = if (view == reload) View.VISIBLE else View.GONE
        list.visibility = if (view == list) View.VISIBLE else View.INVISIBLE
        default.visibility = if (view == default) View.VISIBLE else View.GONE
        empty.visibility = if (view == empty) View.VISIBLE else View.GONE
    }

    companion object {

        fun newIntent(): Intent {
            return Intent(app(), SearchActivity::class.java)
        }

    }

}
