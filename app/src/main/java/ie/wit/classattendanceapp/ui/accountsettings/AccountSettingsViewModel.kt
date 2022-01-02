package ie.wit.classattendanceapp.ui.accountsettings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerUsers
import ie.wit.classattendanceapp.models.UserModel
import timber.log.Timber
import java.lang.Exception

class AccountSettingsViewModel (app: Application) : AndroidViewModel(app) {
    private val student = MutableLiveData<UserModel>()

    var observableStudent: LiveData<UserModel>
        get() = student
        set(value){student.value = value.value}



    fun getStudent(uid:String) {
        Timber.i("$uid")
        try {
            FirebaseDBManagerUsers.findById(uid, student)
            Timber.i("Detail getStudent() Success : ${
                student.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Detail getStudent() Error : $e.message")
        }
    }

    fun updateUser(student: UserModel){
        try {
            FirebaseDBManagerUsers.updateUser(student)
            Timber.i("Detail update() Success : $student")
        } catch (e: Exception) {
            Timber.i("Detail update() Error : $e.message")
        }
    }
}