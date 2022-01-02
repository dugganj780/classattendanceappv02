package ie.wit.classattendanceapp.main

import android.app.Application
import ie.wit.classattendanceapp.models.*
import timber.log.Timber
import timber.log.Timber.i

class ClassAttendanceApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Class App started")
    }
}