package uk.ac.tees.mad.foodreview

import android.app.Application
import uk.ac.tees.mad.foodreview.di.AppModule
import uk.ac.tees.mad.foodreview.di.AppModuleImpl

class FoodReviewApp : Application() {
    companion object {
        lateinit var appModule: AppModule
    }
    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }

}


/**
 * this is an application class which will create the dependency
 * companion object is used to define the class level variable and can be accessed just by
 * using class name.variable name
 * late init var is used to initialize variable lately but initialize them before using them.
 * override fun onCreate(){
 * super.onCreate()
 * appModule = AppModuleImpl(this)
 * }
 *this function is first executed when activity is first created
 */