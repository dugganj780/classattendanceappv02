package ie.wit.classattendanceapp.ui.moduleselection

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.classattendanceapp.R
import ie.wit.classattendanceapp.adapters.ModuleAdapter
import ie.wit.classattendanceapp.adapters.ModuleSelectionAdapter
import ie.wit.classattendanceapp.adapters.ModuleSelectionListener
import ie.wit.classattendanceapp.databinding.FragmentModuleListBinding
import ie.wit.classattendanceapp.databinding.FragmentModuleSelectionBinding
import ie.wit.classattendanceapp.main.ClassAttendanceApp
import ie.wit.classattendanceapp.models.ModuleModel
import ie.wit.classattendanceapp.models.UserModel
import ie.wit.classattendanceapp.ui.modulelist.ModuleListFragmentDirections
import ie.wit.classattendanceapp.ui.modulelist.ModuleListViewModel

class ModuleSelectionFragment: Fragment(), ModuleSelectionListener {
    lateinit var app: ClassAttendanceApp
    private var _fragBinding: FragmentModuleSelectionBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var moduleSelectionViewModel: ModuleSelectionViewModel
    var student = UserModel()
    var module = ModuleModel()
    var modules:MutableList<ModuleModel> = mutableListOf(ModuleModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentModuleSelectionBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        fragBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        moduleSelectionViewModel = ViewModelProvider(this).get(ModuleSelectionViewModel::class.java)
        moduleSelectionViewModel.observableModuleList.observe(viewLifecycleOwner, Observer {
                modules ->
            modules?.let {
                render(modules as ArrayList<ModuleModel>)
            }

        })
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun render(moduleList: ArrayList<ModuleModel>) {
        fragBinding.recyclerView.adapter = ModuleSelectionAdapter(moduleList,this)

    }

    override fun onModuleAdd(module: ModuleModel){
        modules.add(module)
    }

    override fun onModuleRemove(module: ModuleModel){

    }


    override fun onResume() {
        super.onResume()
        moduleSelectionViewModel.load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}