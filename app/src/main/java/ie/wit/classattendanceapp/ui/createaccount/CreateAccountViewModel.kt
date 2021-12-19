package ie.wit.classattendanceapp.ui.createaccount

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import ie.wit.donationx.firebase.FirebaseAuthManager

class CreateAccountViewModel (app: Application) : AndroidViewModel(app) {

    var firebaseAuthManager : FirebaseAuthManager = FirebaseAuthManager(app)
    var liveFirebaseUser : MutableLiveData<FirebaseUser> = firebaseAuthManager.liveFirebaseUser

    fun register(email: String?, password: String?) {
        firebaseAuthManager.register(email, password)
    }
}