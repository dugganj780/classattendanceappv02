package ie.wit.classattendanceapp.ui.moduleadd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ie.wit.classattendanceapp.databinding.FragmentModuleAddBinding
import ie.wit.classattendanceapp.models.LectureModel
import ie.wit.classattendanceapp.models.ModuleModel
import java.util.*

class ModuleAddFragment: Fragment() {
    private lateinit var moduleAddViewModel: ModuleAddViewModel
    private var _fragBinding: FragmentModuleAddBinding? = null
    private val fragBinding get() = _fragBinding!!

    var module = ModuleModel()
    var lecture01 = LectureModel()
    var lecture02 = LectureModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentModuleAddBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        moduleAddViewModel = ViewModelProvider(this).get(ModuleAddViewModel::class.java)

        fragBinding.btnUpdateInfo.setOnClickListener {

            module.moduleCode = fragBinding.moduleCode.text.toString()
            module.title = fragBinding.moduleTitle.text.toString()
            lecture01.startTime = fragBinding.lecture01StartTime.text.toString()
            lecture01.endTime = fragBinding.lecture01EndTime.text.toString()
            lecture01.day = fragBinding.lecture01Day.text.toString()
            lecture01.location = fragBinding.lecture01Location.text.toString()
            lecture02.startTime = fragBinding.lecture02StartTime.text.toString()
            lecture02.endTime = fragBinding.lecture02EndTime.text.toString()
            lecture02.day = fragBinding.lecture02Day.text.toString()
            lecture02.location = fragBinding.lecture02location.text.toString()
            lecture01.id = UUID.randomUUID().toString()
            lecture02.id = UUID.randomUUID().toString()


            module.lectures.add(lecture01.copy())
            module.lectures.add(lecture02.copy())

            addModule(module)


            val action =
                ModuleAddFragmentDirections.actionModuleAddFragmentToModuleListFragment()
            findNavController().navigate(action)
        }
        return root
    }

    private fun addModule(module: ModuleModel){
        moduleAddViewModel.addModule(module)
    }

}