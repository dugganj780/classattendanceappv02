package ie.wit.classattendanceapp.ui.login

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
import ie.wit.classattendanceapp.databinding.LoginFirstBinding


class FirstLogin: AppCompatActivity() {
    private lateinit var loginViewModel : LoginViewModel
    private lateinit var loginFirstBinding : LoginFirstBinding
    var student = UserModel()
    var foundStudent = UserModel()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginFirstBinding = LoginFirstBinding.inflate(layoutInflater)
        setContentView(loginFirstBinding.root)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        //loginViewModel.getStudent(FirebaseAuth.getInstance().currentUser!!.uid)
        Timber.i("${loginViewModel.observableStudent}")

        loginViewModel.observableStudent.observe(this, Observer{
                student -> if (student != null){
                    Timber.i("Observable Student $student")
            foundStudent = student
            Timber.i("Found Student is $foundStudent")
        }
        })

        loginFirstBinding.btnUpdateInfo.setOnClickListener {

            student.uid = FirebaseAuth.getInstance().currentUser!!.uid
            student.firstName = loginFirstBinding.firstName.text.toString()
            student.surname = loginFirstBinding.surname.text.toString()
            student.studentID = loginFirstBinding.studentId.text.toString().toLong()
            addUser(student)
            val launcherIntent = Intent(this, Home::class.java)
            startActivityForResult(launcherIntent, 0)

        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        //loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        loginViewModel.liveFirebaseUser.observe(this, Observer
        { firebaseUser -> if (firebaseUser != null){
            Timber.i("step 1 ${firebaseUser.uid}")
            if (foundStudent.uid == FirebaseAuth.getInstance().currentUser!!.uid){
                val launcherIntent = Intent(this, Home::class.java)
                startActivityForResult(launcherIntent, 0)
            }
        }
        })

    }

    override fun onResume() {
        super.onResume()
        Timber.i("Logged in as ${loginViewModel.liveFirebaseUser.value?.uid}")
        loginViewModel.getStudent(FirebaseAuth.getInstance().currentUser!!.uid)
    }

    private fun addUser(student: UserModel){
        loginViewModel.addUser(student)
    }

    //Required to exit app from Login Screen - must investigate this further
}