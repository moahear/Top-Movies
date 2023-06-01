package com.gamil.moahear.topmovies.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gamil.moahear.topmovies.utils.Constants

@Entity(tableName = Constants.FAVORITE_MOVIES_TABLE)
data class FavoriteMovieEntity(
    @PrimaryKey
    var id: Int = 0,
    var title: String = "",
    var year: String = "",
    var country: String = "",
    var rate: String = "",
    var poster: String = ""

)
