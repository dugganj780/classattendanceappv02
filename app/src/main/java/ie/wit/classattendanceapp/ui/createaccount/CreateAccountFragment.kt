package ie.wit.classattendanceapp.ui.createaccount

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ie.wit.classattendanceapp.R
import ie.wit.classattendanceapp.databinding.FragmentCreateAccountBinding
import ie.wit.classattendanceapp.databinding.FragmentLoginBinding
import ie.wit.classattendanceapp.models.UserManager
import ie.wit.classattendanceapp.models.UserModel
import ie.wit.classattendanceapp.ui.login.LoginFragmentDirections
import timber.log.Timber

class CreateAccountFragment: Fragment() {
    private var _fragBinding: FragmentCreateAccountBinding? = null
    private val fragBinding get() = _fragBinding!!
    var student = UserModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _fragBinding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        // activity?.title = getString(R.string.action_donate)

        setCreateButtonListener(fragBinding)

        return root;
    }

    fun setCreateButtonListener(layout: FragmentCreateAccountBinding) {
        layout.btnCreateAccount.setOnClickListener {
            student.firstName = fragBinding.firstName.text.toString()
            student.surname = fragBinding.surname.text.toString()
            student.studentID = fragBinding.studentId.text.toString().toLong()
            student.password = fragBinding.password.text.toString()

            //Validation to ensure User inputs to all fields
            if (student.firstName.isEmpty() || student.surname.isEmpty() || student.password.isEmpty() ) {
                Snackbar.make(it, R.string.data_missing, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                UserManager.createUser(student.copy())
                val action = CreateAccountFragmentDirections.actionCreateAccountFragmentToModuleListFragment(student.studentID)
                findNavController().navigate(action)
                }
        }
    }
}