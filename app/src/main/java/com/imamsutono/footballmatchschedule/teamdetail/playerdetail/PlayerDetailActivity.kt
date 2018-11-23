package com.imamsutono.footballmatchschedule.teamdetail.playerdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.model.Player
import com.imamsutono.footballmatchschedule.model.PlayerResponse
import com.imamsutono.footballmatchschedule.repository.PlayerRepository
import com.imamsutono.footballmatchschedule.util.invisible
import com.imamsutono.footballmatchschedule.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*
import org.jetbrains.anko.find

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {

    private lateinit var presenter: PlayerDetailPresenter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        supportActionBar?.title = "Player Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = PlayerDetailPresenter(this, PlayerRepository())
        progressBar = progressbar_player_detail

        val id = intent.getStringExtra("id")

        presenter.getPlayerDetail(id)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerDetail(data: List<Player>?) {
        val player = data?.get(0)
        val imgFanart: ImageView = find(R.id.img_player_fanart)

        supportActionBar?.title = player?.playerName

        Picasso.get().load(player?.playerFanart).into(imgFanart)
        tv_player_height.text = player?.playerHeight
        tv_player_weight.text = player?.playerWeight
        tv_player_position_detail.text = player?.playerPosition
        tv_player_description.text = player?.playerDescription
    }

    override fun onDataLoaded(data: PlayerResponse?) {
        showPlayerDetail(data?.detail)
    }

    override fun onDataError() {
    }
}
