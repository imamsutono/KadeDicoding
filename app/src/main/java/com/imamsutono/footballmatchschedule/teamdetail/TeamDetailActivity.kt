package com.imamsutono.footballmatchschedule.teamdetail

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
//import android.view.Menu
import android.widget.ImageView
import android.widget.ProgressBar
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.R.menu.detail_menu
import com.imamsutono.footballmatchschedule.db.FavoriteTeam
import com.imamsutono.footballmatchschedule.db.database
import com.imamsutono.footballmatchschedule.model.Team
import com.imamsutono.footballmatchschedule.model.TeamResponse
import com.imamsutono.footballmatchschedule.repository.TeamRepository
import com.imamsutono.footballmatchschedule.util.invisible
import com.imamsutono.footballmatchschedule.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.fragment_team_overview.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var presenter: TeamDetailPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var teams: Team

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0.0f

        presenter = TeamDetailPresenter(this, TeamRepository())
        progressBar = find(R.id.progressbar_team_detail)

        id = intent.getStringExtra("id")

        presenter.getTeamDetail(id)

        val fragmentAdapter = TeamPagerAdapter(supportFragmentManager)
        viewpager_team.adapter = fragmentAdapter
        tab_team.setupWithViewPager(viewpager_team)

        favoriteState()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(data: List<Team>?) {
        val team = data?.get(0)
        val imgBadge: ImageView = find(R.id.img_team_badge_detail)

        Picasso.get().load(team?.badge).into(imgBadge)

        tv_team_name_detail.text = team?.teamName
        tv_team_formed_year.text = team?.teamFormedYear
        tv_team_stadium.text = team?.teamStadium
        tv_team_overview.text = team?.teamDescription

        teams = Team(team?.teamId, team?.teamName, team?.badge, team?.teamFormedYear, team?.teamStadium, team?.teamDescription)
        menuItem?.getItem(0)?.isVisible = true
    }

    override fun onTeamLoaded(data: TeamResponse?, team: String) {
        showTeamDetail(data?.data)
    }

    override fun onTeamError() {
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
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs("(TEAM_ID = {id})", "id" to id)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.TEAM_ID to teams.teamId,
                        FavoriteTeam.TEAM_NAME to teams.teamName,
                        FavoriteTeam.TEAM_BADGE to teams.badge)
            }

            toast("Added to Favorite")
        } catch (e: SQLiteConstraintException) {
            longToast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                        "id" to id)
            }

            toast("Removed to Favorite")
        } catch (e: SQLiteConstraintException) {
            longToast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}
