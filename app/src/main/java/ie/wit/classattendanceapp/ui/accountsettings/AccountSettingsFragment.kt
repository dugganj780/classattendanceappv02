package ie.wit.classattendanceapp.ui.accountsettings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import ie.wit.classattendanceapp.databinding.FragmentAccountSettingsBinding
import ie.wit.classattendanceapp.main.ClassAttendanceApp
import ie.wit.classattendanceapp.models.UserModel
import ie.wit.classattendanceapp.ui.attendance.AttendanceFragmentArgs
import timber.log.Timber

class AccountSettingsFragment : Fragment(){
    lateinit var app: ClassAttendanceApp
    private var _fragBinding: FragmentAccountSettingsBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var accountSettingsViewModel: AccountSettingsViewModel
    var foundStudent=UserModel()
    //val loginViewModel : LoginViewModel by activityViewModels()

    private val args by navArgs<AttendanceFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentAccountSettingsBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        accountSettingsViewModel = ViewModelProvider(this).get(AccountSettingsViewModel::class.java)
        accountSettingsViewModel.observableStudent.observe(viewLifecycleOwner, Observer { student ->
            student?.let {
                Timber.i("Student: $student")
                foundStudent.modules = student.modules
                Timber.i("FoundStudentModules: ${foundStudent.modules}")
                //render(moduleSignInList as ArrayList<SignInModel>)
                // checkSwipeRefresh()
            }
        })
            fragBinding.btnUpdateInfo.setOnClickListener {


                foundStudent.uid = FirebaseAuth.getInstance().currentUser!!.uid
                foundStudent.firstName = fragBinding.firstName.text.toString()
                foundStudent.surname = fragBinding.surname.text.toString()
                foundStudent.studentID = fragBinding.studentId.text.toString().toLong()
            if(foundStudent.studentID.equals(100100)){
                foundStudent.Admin = true
            }
            updateUser(foundStudent)
                val action =
                    AccountSettingsFragmentDirections.actionAccountSettingsFragmentToModuleListFragment()
                findNavController().navigate(action)

        }

        return root
    }

    override fun onStart() {
        super.onStart()
        accountSettingsViewModel.getStudent(FirebaseAuth.getInstance().currentUser!!.uid)
    }

    private fun updateUser(user:UserModel){
        accountSettingsViewModel.updateUser(user)
    }


}