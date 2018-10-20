package com.imamsutono.footballmatchschedule.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteMatchs.TABLE_FAVORITE_MATCHS, true,
                FavoriteMatchs.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatchs.EVENT_ID to TEXT + UNIQUE,
                FavoriteMatchs.EVENT_DATE to TEXT,
                FavoriteMatchs.HOME_TEAM to TEXT,
                FavoriteMatchs.AWAY_TEAM to TEXT,
                FavoriteMatchs.HOME_SCORE to TEXT,
                FavoriteMatchs.AWAY_SCORE to TEXT)

        db.createTable(FavoriteTeams.TABLE_FAVORITE_TEAMS, true,
                FavoriteTeams.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeams.TEAM_ID to TEXT + UNIQUE,
                FavoriteTeams.TEAM_NAME to TEXT,
                FavoriteTeams.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteMatchs.TABLE_FAVORITE_MATCHS, true)
        db.dropTable(FavoriteTeams.TABLE_FAVORITE_TEAMS, true)
    }
}

// Access property for context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)