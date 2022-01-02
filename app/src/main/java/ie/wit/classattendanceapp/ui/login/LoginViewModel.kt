package ie.wit.classattendanceapp.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import ie.wit.classattendanceapp.firebase.FirebaseAuthManager
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerUsers
import ie.wit.classattendanceapp.models.UserModel
import timber.log.Timber
import java.lang.Exception

class LoginViewModel (app: Application) : AndroidViewModel(app) {

    var firebaseAuthManager : FirebaseAuthManager = FirebaseAuthManager(app)
    var liveFirebaseUser : MutableLiveData<FirebaseUser> = firebaseAuthManager.liveFirebaseUser
    var loggedOut : MutableLiveData<Boolean> = firebaseAuthManager.loggedOut
    private val student = MutableLiveData<UserModel>()

    var observableStudent: LiveData<UserModel>
        get() = student
        set(value){student.value = value.value}



    fun login(email: String?, password: String?) {
        firebaseAuthManager.login(email, password)
    }

    fun logOut() {
        firebaseAuthManager.logOut()
    }

    fun addUser(student: UserModel) {
        FirebaseDBManagerUsers.createUser(student)
    }

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