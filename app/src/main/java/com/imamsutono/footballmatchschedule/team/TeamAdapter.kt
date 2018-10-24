package com.imamsutono.footballmatchschedule.team

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class TeamAdapter(private val teams: List<Team>, private val listener: (Team) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder =
            TeamViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false))

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }
}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val teamNameTxt: TextView = view.find(R.id.tv_team_name)
    private val teamBadgeImg: ImageView = view.find(R.id.img_team_badge)

    fun bindItem(team: Team, listener: (Team) -> Unit) {
        val (_, teamName, badge) = team

        teamNameTxt.text = teamName
        Picasso.get().load(badge).into(teamBadgeImg)
        itemView.setOnClickListener { listener(team) }
    }
}