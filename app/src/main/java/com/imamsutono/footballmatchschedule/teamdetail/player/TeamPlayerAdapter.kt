package com.imamsutono.footballmatchschedule.teamdetail.player

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.model.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class TeamPlayerAdapter(private val players: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<TeamPlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamPlayerViewHolder =
            TeamPlayerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false))

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: TeamPlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }
}

class TeamPlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val txtName: TextView = view.find(R.id.tv_player_name)
    private val txtPosition: TextView = view.find(R.id.tv_player_position)
    private val imgPlayer: ImageView = view.find(R.id.img_player)

    fun bindItem(player: Player, listener: (Player) -> Unit) {
        val (_, playerPhoto, playerName, playerPosition) = player

        Picasso.get().load(playerPhoto).into(imgPlayer)
        txtName.text = playerName
        txtPosition.text = playerPosition
        itemView.setOnClickListener { listener(player) }
    }
}
