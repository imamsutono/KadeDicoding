package com.imamsutono.footballmatchschedule.matchs

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.model.Match
import org.jetbrains.anko.find

class MatchAdapter(private val matchs: List<Match>, private val listener: (Match) -> Unit)
    : RecyclerView.Adapter<MatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
            MatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false))

    override fun getItemCount(): Int = matchs.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matchs[position], listener)
    }
}

class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val dateEventTxt: TextView = view.find(R.id.date_event)
    private val homeTeamTxt: TextView = view.find(R.id.home_team)
    private val scoreTxt: TextView = view.find(R.id.prev_match_score)
    private val awayTeamTxt: TextView = view.find(R.id.away_team)

    fun bindItem(matchs: Match, listener: (Match) -> Unit) {
        val (idEvent, dateEvent, homeTeam, awayTeam, homeScore, awayScore) = matchs
        val scores = if (homeScore != null) "$homeScore vs $awayScore" else ""

        dateEventTxt.text = dateEvent
        homeTeamTxt.text = homeTeam
        scoreTxt.text = scores
        awayTeamTxt.text = awayTeam
        itemView.setOnClickListener { listener(matchs) }
    }
}