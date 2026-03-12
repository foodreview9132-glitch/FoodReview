package uk.ac.tees.mad.foodreview.domain.model

import com.google.firebase.Timestamp
import kotlin.random.Random
import kotlin.time.Clock

data class User(
    val userEmail : String ,
    val createdAt : Long = System.currentTimeMillis() ,
)
