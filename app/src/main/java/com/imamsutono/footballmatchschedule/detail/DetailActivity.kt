package com.imamsutono.footballmatchschedule.detail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.imamsutono.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.imamsutono.footballmatchschedule.R.id.add_to_favorite
import com.imamsutono.footballmatchschedule.R.menu.detail_menu
import com.imamsutono.footballmatchschedule.db.Favorite
import com.imamsutono.footballmatchschedule.db.database
import com.imamsutono.footballmatchschedule.model.*
import com.imamsutono.footballmatchschedule.util.invisible
import com.imamsutono.footballmatchschedule.util.visible
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.lineup.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(), DetailView {
    private lateinit var presenter: DetailPresenter
    private lateinit var matchs: MatchData
    private lateinit var linearLayout: LinearLayout
    private lateinit var progressBar: ProgressBar

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = DetailPresenter(this)
        linearLayout = detail_view
        progressBar = detail_progress_bar
        id = intent.getStringExtra("id")

        presenter.getMatchDetail(id)
        favoriteState()
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})", "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        linearLayout.invisible()
        progressBar.visible()
    }

    override fun hideLoading() {
        linearLayout.visible()
        progressBar.invisible()
    }

    override fun showMatchDetail(datas: List<MatchDetailData>) {
        val data = datas[0]
        val scores = if (data.homeScore != null) "${data.homeScore} vs ${data.awayScore}" else ""

        home_team.text = data.homeTeamName
        home_goal.text = data.homeGoals?.replace(";", "\n")

        date_event.text = data.dateEvent
        score.text = scores

        away_team.text = data.awayTeamName
        away_goal.text = data.awayGoals?.replace(";", "\n")

        home_formation.text = data.homeFormation
        away_formation.text = data.awayFormation
        home_shots.text = data.homeShots
        away_shots.text = data.awayShots

        home_team_name.text = data.homeTeamName
        home_goalkeeper.text = data.homeGoalkeeper
        home_defense.text = data.homeDefense?.replace(";", "\n")
        away_team_name.text = data.awayTeamName
        away_goalkeeper.text = data.awayGoalkeeper
        away_defense.text = data.awayDefense?.replace(";", "\n")

        val homeBadge: ImageView = findViewById(R.id.home_badge)
        val awayBadge: ImageView = findViewById(R.id.away_badge)

        presenter.callTeam(data.idHomeTeam, homeBadge)
        presenter.callTeam(data.idAwayTeam, awayBadge)

        matchs = MatchData(id, data.dateEvent, data.homeTeamName, data.awayTeamName, data.homeScore, data.awayScore)

        menuItem?.getItem(0)?.isVisible = true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        menuItem?.getItem(0)?.isVisible = false

        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to matchs.idEvent,
                        Favorite.EVENT_DATE to matchs.dateEvent,
                        Favorite.HOME_TEAM to matchs.homeTeam,
                        Favorite.AWAY_TEAM to matchs.awayTeam,
                        Favorite.HOME_SCORE to matchs.homeScore,
                        Favorite.AWAY_SCORE to matchs.awayScore)
            }

            toast("Added to Favorite")
        } catch (e: SQLiteConstraintException) {
            longToast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})", "id" to id)
            }

            toast("Removed from Favorite")
        } catch (e: SQLiteConstraintException) {
            longToast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}
