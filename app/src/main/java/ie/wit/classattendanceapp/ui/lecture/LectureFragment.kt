package ie.wit.classattendanceapp.ui.lecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import ie.wit.classattendanceapp.databinding.FragmentLectureBinding
import ie.wit.classattendanceapp.databinding.HomeBinding
import ie.wit.classattendanceapp.models.LectureModel
import ie.wit.classattendanceapp.models.ModuleModel
import ie.wit.classattendanceapp.models.SignInModel
import ie.wit.classattendanceapp.models.UserModel
import ie.wit.classattendanceapp.ui.module.ModuleViewModel
import ie.wit.classattendanceapp.ui.modulelist.ModuleListFragmentDirections
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class LectureFragment : Fragment() {

    private lateinit var lectureViewModel: LectureViewModel
    private val args by navArgs<LectureFragmentArgs>()
    private var _fragBinding: FragmentLectureBinding? = null
    private val fragBinding get() = _fragBinding!!
    var currentStudent = UserModel()
    var currentModule = ModuleModel()
    var currentLecture = LectureModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentLectureBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        lectureViewModel = ViewModelProvider(this).get(LectureViewModel::class.java)

        lectureViewModel.observableStudent.observe(viewLifecycleOwner, Observer { student ->
            student?.let {
                Timber.i("Student modules: ${student.modules}")
                currentStudent = student
                Timber.i("CurrentStudent is $currentStudent")

                if (currentStudent.Admin){
                    fragBinding.btnSeeAttendance.visibility = View.VISIBLE
                }
                else{
                    fragBinding.btnSeeAttendance.visibility = View.GONE
                }
                // checkSwipeRefresh()
            }
        })

        lectureViewModel.observableModule.observe(viewLifecycleOwner, Observer { module ->
            module?.let {
                Timber.i("Modules: ${module}")
                currentModule = module
                val iterator = module.lectures.listIterator()
                for(lecture in iterator){
                    if(lecture.id == args.lectureId){
                        currentLecture = lecture
                    }
                }
                // checkSwipeRefresh()
            }
        })




        fragBinding.btnSignInLive.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            var signIn = SignInModel(
                currentStudent.uid,
                currentStudent.studentID,
                currentStudent.firstName,
                currentStudent.surname,
                currentModule.moduleCode,
                currentModule.uid,
                currentLecture.day,
                currentLecture.startTime,
                currentDate,
                false,
                true,
                false)

            Timber.i("SignIn created: $signIn")
            addSignIn(signIn)
        }


        fragBinding.btnSignInPerson.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            var signIn = SignInModel(
                currentStudent.uid,
                currentStudent.studentID,
                currentStudent.firstName,
                currentStudent.surname,
                currentModule.moduleCode,
                currentModule.uid,
                currentLecture.day,
                currentLecture.startTime,
                currentDate,
                true,
                false,
                false)

            Timber.i("SignIn created: $signIn")
            addSignIn(signIn)
        }
        fragBinding.btnSignInRecording.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            var signIn = SignInModel(
                currentStudent.uid,
                currentStudent.studentID,
                currentStudent.firstName,
                currentStudent.surname,
                currentModule.moduleCode,
                currentModule.uid,
                currentLecture.day,
                currentLecture.startTime,
                currentDate,
                false,
                false,
                true)

            Timber.i("SignIn created: $signIn")
            addSignIn(signIn)
        }

        fragBinding.btnSeeAttendance.setOnClickListener {
            val action =
                LectureFragmentDirections.actionLectureFragmentToAttendanceFragment(args.moduleId)
            findNavController().navigate(action)
        }

        return root
        }


    private fun render() {
        // fragBinding.editAmount.setText(donation.amount.toString())
        // fragBinding.editPaymenttype.text = donation.paymentmethod
        fragBinding.lecturevm = lectureViewModel
    }

    override fun onResume() {
        super.onResume()
        //fragBinding.lecturevm = lectureViewModel

        lectureViewModel.getModule(args.moduleId)
        lectureViewModel.getStudent(FirebaseAuth.getInstance().currentUser!!.uid)
        //currentLecture = lectureViewModel.getLecture(currentModule, args.lectureId)

    }

    override fun onStart() {
        super.onStart()
        //fragBinding.lecturevm = lectureViewModel

        lectureViewModel.getModule(args.moduleId)
        lectureViewModel.getStudent(FirebaseAuth.getInstance().currentUser!!.uid)
        //currentLecture = lectureViewModel.getLecture(currentModule, args.lectureId)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    private fun addSignIn(signIn: SignInModel){
        lectureViewModel.addSignIn(signIn)
    }
}