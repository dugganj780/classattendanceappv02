package ie.wit.classattendanceapp.ui.login

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ie.wit.classattendanceapp.activities.Home
import ie.wit.classattendanceapp.models.UserModel
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
            if(student.studentID.equals(0)){
                student.Admin = true
        }
            addUser(student)
            val launcherIntent = Intent(this, Home::class.java)
            startActivityForResult(launcherIntent, 0)

        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        //loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.observableStudent.observe(this, Observer{
                student -> if (student != null){
            Timber.i("Observable Student $student")
            foundStudent = student
            Timber.i("Found Student is $foundStudent")
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

}