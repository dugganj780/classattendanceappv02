package ie.wit.classattendanceapp.ui.attendance

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.classattendanceapp.R
import ie.wit.classattendanceapp.databinding.FragmentAttendanceBinding
import ie.wit.classattendanceapp.main.ClassAttendanceApp
import ie.wit.classattendanceapp.adapters.SignInAdapter
import ie.wit.classattendanceapp.adapters.SignInListener
import ie.wit.classattendanceapp.models.SignInModel
import timber.log.Timber

class AttendanceFragment : Fragment(), SignInListener {
    lateinit var app: ClassAttendanceApp
    private var _fragBinding: FragmentAttendanceBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var attendanceViewModel: AttendanceViewModel

    private val args by navArgs<AttendanceFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentAttendanceBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        fragBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        attendanceViewModel = ViewModelProvider(this).get(AttendanceViewModel::class.java)
        attendanceViewModel.observableModuleSignInList.observe(viewLifecycleOwner, Observer { moduleSignInList ->
            moduleSignInList?.let {
                Timber.i("Module SignIns: ${moduleSignInList}")
                render(moduleSignInList as ArrayList<SignInModel>)
                // checkSwipeRefresh()
            }
        })

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

    private fun render(moduleSignInList: ArrayList<SignInModel>) {
        fragBinding.recyclerView.adapter = SignInAdapter(moduleSignInList,this)
        if (moduleSignInList.isEmpty()) {
            fragBinding.recyclerView.visibility = View.GONE
        } else {
            fragBinding.recyclerView.visibility = View.VISIBLE
        }
    }

    override fun onSignInClick(signIn: SignInModel) {
        val action = AttendanceFragmentDirections.actionAttendanceFragmentToSignInFragment(signIn.uid)
        findNavController().navigate(action)
    }

    override fun onStart() {
        super.onStart()
        attendanceViewModel.getModule(args.moduleId)
        attendanceViewModel.getModuleSignIns(args.moduleId)

    }

    override fun onResume() {
        super.onResume()
        attendanceViewModel.getModule(args.moduleId)
        attendanceViewModel.getModuleSignIns(args.moduleId)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

}