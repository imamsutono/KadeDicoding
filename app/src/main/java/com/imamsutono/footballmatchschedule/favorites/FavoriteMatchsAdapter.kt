package com.imamsutono.footballmatchschedule.favorites

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.db.Favorite
import com.imamsutono.footballmatchschedule.detail.DetailActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class FavoriteMatchsAdapter(private val favorite: List<Favorite>)
    : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder  =
            FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false))

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position])
    }

}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val dateEventTxt: TextView = view.find(R.id.date_event)
    private val homeTeamTxt: TextView = view.find(R.id.home_team)
    private val scoreTxt: TextView = view.find(R.id.prev_match_score)
    private val awayTeamTxt: TextView = view.find(R.id.away_team)

    fun bindItem(favorite: Favorite) {
        val (_, idEvent, eventDate, homeTeam, awayTeam, homeScore, awayScore) = favorite
        val scores = if (homeScore != null) "$homeScore vs $awayScore" else ""

        dateEventTxt.text = eventDate
        homeTeamTxt.text = homeTeam
        scoreTxt.text = scores
        awayTeamTxt.text = awayTeam

        itemView.setOnClickListener {
            it?.context?.startActivity<DetailActivity>("id" to idEvent)
        }
    }
}