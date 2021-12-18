package ie.wit.classattendanceapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ie.wit.classattendanceapp.databinding.FragmentLaunchBinding
import ie.wit.classattendanceapp.databinding.FragmentLoginBinding
import ie.wit.classattendanceapp.models.UserManager
import ie.wit.classattendanceapp.models.UserManager.findAllUsers
import ie.wit.classattendanceapp.models.UserModel
import ie.wit.classattendanceapp.ui.launch.LaunchFragmentDirections
import timber.log.Timber

class LoginFragment: Fragment() {
    private var _fragBinding: FragmentLoginBinding? = null
    private val fragBinding get() = _fragBinding!!
    var student = UserModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _fragBinding = FragmentLoginBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        // activity?.title = getString(R.string.action_donate)

        setLoginButtonListener(fragBinding)

        return root;
    }

    fun setLoginButtonListener(layout: FragmentLoginBinding) {
        layout.btnLogin.setOnClickListener {
            student.studentID = fragBinding.studentId.text.toString().toLong()
            student.password = fragBinding.password.text.toString()

            //Iterates through existing users for basic authentication
            val iterator = UserManager.findAllUsers().listIterator()
            for(item in iterator){
                Timber.i("${item.studentID}")
                Timber.i("${item.password}")
                if(item.studentID == student.studentID && item.password==student.password) {
                    student = item
                    Timber.i("${student}")
                    val action = LoginFragmentDirections.actionLoginFragmentToModuleListFragment(student.studentID)
                    findNavController().navigate(action)
                }
                //If user not found, Snackbar informs user that credentials are incorrect
                else{
                    Timber.i("StudentID: ${student.studentID.javaClass} Password: ${student.password.javaClass}")
                    Snackbar.make(it,"Invalid User Credentials", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}