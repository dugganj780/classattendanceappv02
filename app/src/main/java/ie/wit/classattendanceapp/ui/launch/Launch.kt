package ie.wit.classattendanceapp.ui.launch

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ie.wit.classattendanceapp.databinding.LaunchBinding
import ie.wit.classattendanceapp.ui.createaccount.CreateAccount
import ie.wit.classattendanceapp.ui.login.Login
import timber.log.Timber

class Launch: AppCompatActivity() {
    private lateinit var launchViewModel : LaunchViewModel
    private lateinit var launchBinding : LaunchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchBinding = LaunchBinding.inflate(layoutInflater)
        setContentView(launchBinding.root)
        Timber.i("Launched")


        launchBinding.btnLogin.setOnClickListener {
            val launcherIntent = Intent(this, Login::class.java)
            startActivityForResult(launcherIntent, 0)

        }

        launchBinding.btnCreateAccount.setOnClickListener {
            val launcherIntent = Intent(this, CreateAccount::class.java)
            startActivityForResult(launcherIntent, 0)


        }
    }

    fun setLoginButtonListener() {
        launchBinding.btnLogin.setOnClickListener {
            val launcherIntent = Intent(this, Login::class.java)

        }
    }

    fun setCreateAccountButtonListener() {
        launchBinding.btnCreateAccount.setOnClickListener {
            val launcherIntent = Intent(this, CreateAccount::class.java)

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,"Click again to Close App...", Toast.LENGTH_LONG).show()
        finish()
    }

}