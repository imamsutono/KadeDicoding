package com.imamsutono.footballmatchschedule.matchs.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.SearchView
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.detail.DetailActivity
import com.imamsutono.footballmatchschedule.matchs.MatchAdapter
import com.imamsutono.footballmatchschedule.matchs.MatchPresenter
import com.imamsutono.footballmatchschedule.matchs.MatchView
import com.imamsutono.footballmatchschedule.model.Match
import com.imamsutono.footballmatchschedule.model.MatchResponse
import com.imamsutono.footballmatchschedule.repository.MatchRepository
import com.imamsutono.footballmatchschedule.util.invisible
import com.imamsutono.footballmatchschedule.util.visible
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class SearchMatchActivity : AppCompatActivity(), MatchView {

    private lateinit var presenter: MatchPresenter
    private lateinit var list: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var matchs: MutableList<Match> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        presenter = MatchPresenter(this, MatchRepository())
        progressBar = find(R.id.progressbar_match_search)
        list = find(R.id.rv_match_search)

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = MatchAdapter(matchs) {
            startActivity<DetailActivity>("id" to it.idEvent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.queryHint = "Search Match..."

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                searchView.isIconified = false
                searchView.requestFocusFromTouch()
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                searchView.setQuery("", true)
                return true
            }

        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean = false

            override fun onQueryTextChange(queryText: String?): Boolean {
                presenter.searchMatch(queryText)
                return true
            }

        })

        searchItem.actionView = searchView
        searchItem.expandActionView()

        return true
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchs(data: List<Match>?) {
        matchs.clear()

        if (data != null) {
            matchs.addAll(data)
        }

        list.adapter.notifyDataSetChanged()
    }

    override fun onDataLoaded(data: MatchResponse?) {
        showMatchs(data?.search)
    }

    override fun onDataError() {
    }
}
