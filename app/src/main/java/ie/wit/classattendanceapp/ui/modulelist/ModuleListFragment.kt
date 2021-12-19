package ie.wit.classattendanceapp.ui.modulelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ie.wit.classattendanceapp.R
import ie.wit.classattendanceapp.main.ClassAttendanceApp
import ie.wit.classattendanceapp.adapters.ModuleAdapter
import ie.wit.classattendanceapp.adapters.ModuleListener
import ie.wit.classattendanceapp.databinding.FragmentModuleListBinding
import ie.wit.classattendanceapp.models.ModuleModel
import ie.wit.classattendanceapp.models.UserModel
import timber.log.Timber


class ModuleListFragment : Fragment(), ModuleListener {
    lateinit var app: ClassAttendanceApp
    private var _fragBinding: FragmentModuleListBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var moduleListViewModel: ModuleListViewModel
    private val args by navArgs<ModuleListFragmentArgs>()
    var student = UserModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentModuleListBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        fragBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        moduleListViewModel = ViewModelProvider(this).get(ModuleListViewModel::class.java)
        moduleListViewModel.observableModuleList.observe(viewLifecycleOwner, Observer {
                modules ->
            modules?.let {
                render(modules as ArrayList<ModuleModel>)
                checkSwipeRefresh()
            }

        })

        setSwipeRefresh()
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
        fragBinding.recyclerView.adapter = ModuleAdapter(moduleList,this)
        if (moduleList.isEmpty()) {
            fragBinding.recyclerView.visibility = View.GONE
            fragBinding.modulesNotFound.visibility = View.VISIBLE
        } else {
            fragBinding.recyclerView.visibility = View.VISIBLE
            fragBinding.modulesNotFound.visibility = View.GONE
        }
    }

    override fun onModuleClick(module: ModuleModel){
        val action = ModuleListFragmentDirections.actionModuleListFragmentToModuleFragment(module.id)
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        moduleListViewModel.getUserModules(args.studentId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    fun setSwipeRefresh() {
        fragBinding.swiperefresh.setOnRefreshListener {
            fragBinding.swiperefresh.isRefreshing = true
            moduleListViewModel.getUserModules(args.studentId)        }
    }

    fun checkSwipeRefresh() {
        if (fragBinding.swiperefresh.isRefreshing)
            fragBinding.swiperefresh.isRefreshing = false
    }
/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadModules(){
        app.students.findUserModules(student)?.let { showModules(it) }
    }

    fun showModules (modules: List<ModuleModel>) {
        binding.recyclerView.adapter = ModuleAdapter(modules, this)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { loadModules() }
    }

 */
}

{
    "rules": {
    "users": {
    "$uid:{
    ".write": "auth.uid != null",
    ".read": "auth.uid != null"
}
},
    "modules": {
    "$uid": {
    ".write": "$uid === auth.uid",
    ".read": "$uid === auth.uid"
}
},
    "signIns": {
    "$uid": {
    ".write": "$uid === auth.uid",
    ".read": "$uid === auth.uid"
}
},
}
}


