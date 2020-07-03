package com.liebersonsantos.tmdbproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.liebersonsantos.tmdbproject.data.database.converters.Converters
import com.liebersonsantos.tmdbproject.data.database.modeldb.FavoriteMovieDAO
import com.liebersonsantos.tmdbproject.data.database.modeldb.FavoriteMovies
import com.liebersonsantos.tmdbproject.data.database.modeldb.User
import com.liebersonsantos.tmdbproject.data.database.modeldb.UserDAO

@Database(entities = [User::class, FavoriteMovies::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TmdbDataBase : RoomDatabase() {

    abstract fun userDao(): UserDAO
    abstract fun favoriteMovieDao(): FavoriteMovieDAO

    companion object {
        @Volatile
        private var INSTANCE: TmdbDataBase? = null

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //caso haja alguma atualizaçao no db
            }
        }

        fun getDb(context: Context): TmdbDataBase{
            val tempIntance = INSTANCE
            if (tempIntance != null){
                return tempIntance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TmdbDataBase::class.java, "tmdb_db")
                    .addMigrations(MIGRATION_1_2)
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}