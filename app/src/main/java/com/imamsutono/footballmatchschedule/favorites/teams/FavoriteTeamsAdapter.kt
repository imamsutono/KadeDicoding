package com.imamsutono.footballmatchschedule.favorites.teams

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.db.FavoriteTeams
import com.imamsutono.footballmatchschedule.detail.DetailActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class FavoriteTeamsAdapter(private val favorite: List<FavoriteTeams>)
    : RecyclerView.Adapter<FavoriteTeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamsViewHolder =
            FavoriteTeamsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false))

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteTeamsViewHolder, position: Int) {
        holder.bindItem(favorite[position])
    }
}

class FavoriteTeamsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imgTeamBadge: ImageView = view.find(R.id.img_team_badge)
    private val txtTeamName: TextView = view.find(R.id.tv_team_name)

    fun bindItem(favorite: FavoriteTeams) {
        val (_, teamId, teamName, teamBadge) = favorite

        txtTeamName.text = teamName
        Picasso.get().load(teamBadge).into(imgTeamBadge)

        itemView.setOnClickListener {
            it?.context?.startActivity<DetailActivity>("id" to teamId)
        }
    }
}