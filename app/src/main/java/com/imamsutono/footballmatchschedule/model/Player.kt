package com.imamsutono.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
    @SerializedName("player")
    val data: List<Player>?,

    @SerializedName("players")
    val detail: List<Player>?
)

data class Player(
    @SerializedName("idPlayer")
    val playerId: String?,

    @SerializedName("strCutout")
    val playerPhoto: String?,

    @SerializedName("strPlayer")
    val playerName: String?,

    @SerializedName("strPosition")
    val playerPosition: String?,

    @SerializedName("strDescriptionEN")
    val playerDescription: String?,

    @SerializedName("strHeight")
    val playerHeight: String?,

    @SerializedName("strWeight")
    val playerWeight: String?,

    @SerializedName("strFanart1")
    val playerFanart: String?
)
