package com.imamsutono.footballmatchschedule.teamdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
//import android.view.Menu
import android.widget.ImageView
import android.widget.ProgressBar
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.model.Team
import com.imamsutono.footballmatchschedule.model.TeamResponse
import com.imamsutono.footballmatchschedule.repository.TeamRepository
import com.imamsutono.footballmatchschedule.util.invisible
import com.imamsutono.footballmatchschedule.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.fragment_team_overview.*
import org.jetbrains.anko.find

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var presenter: TeamDetailPresenter
    private lateinit var progressBar: ProgressBar

//    private var menuItem: Menu? = null
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = TeamDetailPresenter(this, TeamRepository())
        progressBar = find(R.id.progressbar_team_detail)

        id = intent.getStringExtra("id")

        presenter.getTeamDetail(id)

        val fragmentAdapter = TeamPagerAdapter(supportFragmentManager)
        viewpager_team.adapter = fragmentAdapter

        tab_team.setupWithViewPager(viewpager_team)
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
    }

    override fun onTeamLoaded(data: TeamResponse?, team: String) {
        showTeamDetail(data?.data)
    }

    override fun onTeamError() {
    }
}
