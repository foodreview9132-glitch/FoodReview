package uk.ac.tees.mad.foodreview.domain.repository

interface AuthRepository {
    suspend fun signIn(email : String,
                       password : String) : Result<Unit>

    suspend fun signUp(email : String ,
                       password : String) : Result<Unit>

    suspend fun signOut() : Result<Unit>

}


/**
 * It is an AuthRepository interface , abstraction layer , it will be implemented by a class
 * AuthRepository Impl .It has methods like SignIn() , SignOut() , SignUp() .
 * These methods returns the Result<Unit> means they will return success or failure .
 */