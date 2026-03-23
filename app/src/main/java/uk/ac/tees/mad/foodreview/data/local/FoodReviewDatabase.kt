package uk.ac.tees.mad.foodreview.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FoodReviewEntity::class] ,
    version = 1 ,
    exportSchema = false
)
abstract class FoodReviewDatabase : RoomDatabase(){
    abstract fun getReviewDao() : FoodReviewDao
}