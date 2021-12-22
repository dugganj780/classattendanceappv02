package ie.wit.classattendanceapp.ui.createaccount

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ie.wit.classattendanceapp.R
import ie.wit.classattendanceapp.activities.Home
import ie.wit.classattendanceapp.databinding.CreateAccountBinding
import ie.wit.classattendanceapp.databinding.LoginBinding
import ie.wit.classattendanceapp.models.UserManager
import ie.wit.classattendanceapp.models.UserModel
import ie.wit.classattendanceapp.ui.login.LoginViewModel
import timber.log.Timber
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import ie.wit.classattendanceapp.ui.login.Login


class CreateAccount: AppCompatActivity() {
    private lateinit var createAccountViewModel : CreateAccountViewModel
    private lateinit var createAccountBinding : CreateAccountBinding
    var student =UserModel()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAccountBinding = CreateAccountBinding.inflate(layoutInflater)
        setContentView(createAccountBinding.root)

        createAccountBinding.btnCreateAccount.setOnClickListener {

            register(createAccountBinding.email.text.toString(),
                createAccountBinding.password.text.toString())
            val launcherIntent = Intent(this, Login::class.java)
            startActivityForResult(launcherIntent, 0)

        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        createAccountViewModel = ViewModelProvider(this).get(CreateAccountViewModel::class.java)

    }

    private fun register(email: String, password: String) {
        Timber.d("signIn:$email")
        if (!validateForm()) {
            return
        }

        createAccountViewModel.register(email, password)
    }



    private fun checkStatus(error:Boolean) {
        if (error)
            Toast.makeText(this,
                getString(R.string.auth_failed),
                Toast.LENGTH_LONG).show()
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = createAccountBinding.email.text.toString()
        if (TextUtils.isEmpty(email)) {
            createAccountBinding.email.error = "Required."
            valid = false
        } else {
            createAccountBinding.email.error = null
        }

        val password = createAccountBinding.password.text.toString()
        if (TextUtils.isEmpty(password)) {
            createAccountBinding.password.error = "Required."
            valid = false
        } else {
            createAccountBinding.password.error = null
        }
        return valid
    }

    //Required to exit app from Login Screen - must investigate this further
}