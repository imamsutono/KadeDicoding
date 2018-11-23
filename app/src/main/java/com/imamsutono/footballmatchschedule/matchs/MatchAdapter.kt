package com.imamsutono.footballmatchschedule.matchs

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.model.Match
import com.imamsutono.footballmatchschedule.util.getDate
import com.imamsutono.footballmatchschedule.util.toGMTFormat
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

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
        val (_, dateEvent, homeTeam, awayTeam, homeScore, awayScore, timeEvent) = matchs
        val date = dateEvent?.replace("-", "/")
        val scores = if (homeScore != null) "$homeScore vs $awayScore" else ""

        val dateTime = if (timeEvent != null) {
            val timeFormatted = toGMTFormat(date, timeEvent)
            "${getDate(Date(date))} - ${SimpleDateFormat("HH:mm").format(timeFormatted)}"
        } else {
            "${getDate(Date(date))} - "
        }

        dateEventTxt.text = dateTime
        homeTeamTxt.text = homeTeam
        scoreTxt.text = scores
        awayTeamTxt.text = awayTeam
        itemView.setOnClickListener { listener(matchs) }
    }
}