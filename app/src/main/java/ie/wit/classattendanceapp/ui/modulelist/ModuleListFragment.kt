package ie.wit.classattendanceapp.ui.modulelist


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import ie.wit.classattendanceapp.R
import ie.wit.classattendanceapp.main.ClassAttendanceApp
import ie.wit.classattendanceapp.adapters.ModuleAdapter
import ie.wit.classattendanceapp.adapters.ModuleListener
import ie.wit.classattendanceapp.databinding.FragmentModuleListBinding
import ie.wit.classattendanceapp.models.ModuleModel
import ie.wit.classattendanceapp.ui.login.LoginViewModel
import timber.log.Timber


class ModuleListFragment : Fragment(), ModuleListener {
    lateinit var app: ClassAttendanceApp
    private var _fragBinding: FragmentModuleListBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var moduleListViewModel: ModuleListViewModel
    val loginViewModel : LoginViewModel by activityViewModels()


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
        Timber.i("Student is ${loginViewModel.observableStudent}")
        loginViewModel.observableStudent.observe(viewLifecycleOwner, Observer { student ->
            student?.let {
                Timber.i("Student modules: ${student.modules}")
                render(student.modules as ArrayList<ModuleModel>)
                // checkSwipeRefresh()
            }
        })

        setSwipeRefresh()
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    private fun render(moduleList: ArrayList<ModuleModel>) {
        fragBinding.recyclerView.adapter = ModuleAdapter(moduleList, this)
        if (moduleList.isEmpty()) {
            fragBinding.recyclerView.visibility = View.GONE
            fragBinding.modulesNotFound.visibility = View.VISIBLE
        } else {
            fragBinding.recyclerView.visibility = View.VISIBLE
            fragBinding.modulesNotFound.visibility = View.GONE
        }
    }

    override fun onModuleClick(module: ModuleModel) {
        val action =
            ModuleListFragmentDirections.actionModuleListFragmentToModuleFragment(module.uid)
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        loginViewModel.getStudent(FirebaseAuth.getInstance().currentUser!!.uid)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    fun setSwipeRefresh() {
        fragBinding.swiperefresh.setOnRefreshListener {
            fragBinding.swiperefresh.isRefreshing = true
            //moduleListViewModel.getUserModules(args.studentId)        }
        }

        fun checkSwipeRefresh() {
            if (fragBinding.swiperefresh.isRefreshing)
                fragBinding.swiperefresh.isRefreshing = false
        }

    }
}

