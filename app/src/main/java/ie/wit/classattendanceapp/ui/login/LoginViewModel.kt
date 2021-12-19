package ie.wit.classattendanceapp.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import ie.wit.donationx.firebase.FirebaseAuthManager

class LoginViewModel (app: Application) : AndroidViewModel(app) {

    var firebaseAuthManager : FirebaseAuthManager = FirebaseAuthManager(app)
    var liveFirebaseUser : MutableLiveData<FirebaseUser> = firebaseAuthManager.liveFirebaseUser
    var loggedOut : MutableLiveData<Boolean> = firebaseAuthManager.loggedOut


    fun login(email: String?, password: String?) {
        firebaseAuthManager.login(email, password)
    }

    fun logOut() {
        firebaseAuthManager.logOut()
    }
}