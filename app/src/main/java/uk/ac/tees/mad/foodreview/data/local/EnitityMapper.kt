package uk.ac.tees.mad.foodreview.data.local

import uk.ac.tees.mad.foodreview.domain.model.Review


fun Review.toFoodReviewEntity() : FoodReviewEntity {
    return FoodReviewEntity(
        id = id,
        foodName = foodName,
        reviewText = reviewText,
        rating = rating,
        timestamp = createdAt ,
        userId = userId
    )
}

fun FoodReviewEntity.toReview(): Review{
    return Review(
        id = id ,
        foodName = foodName ,
        rating = rating ,
        reviewText = reviewText,
        createdAt = timestamp ,
        userId = userId
    )
}