package uk.ac.tees.mad.foodreview.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodReviewDao {
    @Query("SELECT * FROM reviews ORDER BY timestamp DESC LIMIT 50 ")
    fun getReviewsList(): Flow<List<FoodReviewEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(reviews :List<FoodReviewEntity>)

    @Query("DELETE FROM reviews")
    suspend fun clearAll()
}