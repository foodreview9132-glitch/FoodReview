package uk.ac.tees.mad.foodreview.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "reviews")
data class FoodReviewEntity(
    @PrimaryKey val id : String ,
    val foodName : String ,
    val reviewText : String ,
    val rating :Int ,
    val userId :String ,
    val timestamp : Long ,
)
