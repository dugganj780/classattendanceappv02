package ie.wit.classattendanceapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ie.wit.classattendanceapp.R
import ie.wit.classattendanceapp.activities.Home
import ie.wit.classattendanceapp.databinding.LaunchBinding
import ie.wit.classattendanceapp.databinding.LoginBinding
import ie.wit.classattendanceapp.models.UserManager
import ie.wit.classattendanceapp.models.UserManager.findAllUsers
import ie.wit.classattendanceapp.models.UserModel
import timber.log.Timber

class Login : AppCompatActivity() {

    private lateinit var loginViewModel : LoginViewModel
    private lateinit var loginBinding : LoginBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = LoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.btnLogin.setOnClickListener {
            signIn(loginBinding.email.text.toString(),
                loginBinding.password.text.toString())
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.liveFirebaseUser.observe(this, Observer
        { firebaseUser -> if (firebaseUser != null)
            startActivity(Intent(this, Home::class.java)) })

        loginViewModel.firebaseAuthManager.errorStatus.observe(this, Observer
        { status -> checkStatus(status) })
    }

    //Required to exit app from Login Screen - must investigate this further

    private fun signIn(email: String, password: String) {

        Timber.d("signIn:$email")
        if (!validateForm()) {
            return
        }

        loginViewModel.login(email, password)
    }

    private fun checkStatus(error:Boolean) {
        if (error)
            Toast.makeText(this,
                getString(R.string.auth_failed),
                Toast.LENGTH_LONG).show()
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = loginBinding.email.text.toString()
        if (TextUtils.isEmpty(email)) {
            loginBinding.email.error = "Required."
            valid = false
        } else {
            loginBinding.email.error = null
        }

        val password = loginBinding.password.text.toString()
        if (TextUtils.isEmpty(password)) {
            loginBinding.password.error = "Required."
            valid = false
        } else {
            loginBinding.password.error = null
        }
        return valid
    }
}