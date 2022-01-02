package ie.wit.classattendanceapp.ui.createaccount

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ie.wit.classattendanceapp.R
import ie.wit.classattendanceapp.databinding.CreateAccountBinding
import ie.wit.classattendanceapp.models.UserModel
import timber.log.Timber
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

}