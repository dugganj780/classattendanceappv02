package ie.wit.classattendanceapp.ui.module

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
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.classattendanceapp.adapters.LectureAdapter
import ie.wit.classattendanceapp.adapters.LectureListener
import ie.wit.classattendanceapp.databinding.FragmentModuleBinding
import ie.wit.classattendanceapp.databinding.FragmentSlideshowBinding
import ie.wit.classattendanceapp.main.ClassAttendanceApp
import ie.wit.classattendanceapp.models.LectureModel
import ie.wit.classattendanceapp.models.ModuleModel
import timber.log.Timber

class ModuleFragment : Fragment(), LectureListener {
    lateinit var app: ClassAttendanceApp
    private val args by navArgs<ModuleFragmentArgs>()
    private var _fragBinding: FragmentModuleBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var moduleViewModel: ModuleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentModuleBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        fragBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        moduleViewModel = ViewModelProvider(this).get(ModuleViewModel::class.java)
        moduleViewModel.observableModule.observe(viewLifecycleOwner, Observer {
            module ->
            module.let {
                Timber.i("lecture view is ${module.lectures}")
                render(module.lectures as ArrayList<LectureModel>)
            }
        })
        return root
    }

    private fun render(lectures: ArrayList<LectureModel>) {
        // fragBinding.editAmount.setText(donation.amount.toString())
        // fragBinding.editPaymenttype.text = donation.paymentmethod
        fragBinding.recyclerView.adapter = LectureAdapter(lectures, this)
        Timber.i("lectures in render method are $lectures")
        if (lectures.isEmpty()) {
            fragBinding.recyclerView.visibility = View.GONE
            fragBinding.lecturesNotFound.visibility = View.VISIBLE
        } else {
            fragBinding.recyclerView.visibility = View.VISIBLE
            fragBinding.lecturesNotFound.visibility = View.GONE
        }
    }

    override fun onLectureClick(lecture: LectureModel) {
        val action = ModuleFragmentDirections.actionModuleFragmentToLectureFragment(lecture.id)
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        Timber.i("module uid is ${args.uid}")
        moduleViewModel.getModule(args.uid)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}