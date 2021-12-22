package ie.wit.classattendanceapp.ui.createaccount

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ie.wit.classattendanceapp.models.UserModel
import ie.wit.classattendanceapp.firebase.FirebaseAuthManager
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerUsers
import timber.log.Timber

class CreateAccountViewModel (app: Application) : AndroidViewModel(app) {

    var firebaseAuthManager : FirebaseAuthManager = FirebaseAuthManager(app)
    var liveFirebaseUser : MutableLiveData<FirebaseUser> = firebaseAuthManager.liveFirebaseUser

    fun register(email: String?, password: String?) {
        firebaseAuthManager.register(email, password)
        var currentUser: String = FirebaseAuth.getInstance().currentUser!!.uid
        Timber.i("Created $currentUser")
    }


}