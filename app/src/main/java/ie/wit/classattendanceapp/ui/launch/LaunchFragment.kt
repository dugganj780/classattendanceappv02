package ie.wit.classattendanceapp.ui.launch

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import ie.wit.classattendanceapp.databinding.FragmentLaunchBinding

class LaunchFragment: Fragment() {
    private var _fragBinding: FragmentLaunchBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _fragBinding = FragmentLaunchBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        // activity?.title = getString(R.string.action_donate)

        setLoginButtonListener(fragBinding)
        setCreateAccountButtonListener(fragBinding)

        return root;
    }

//    companion object {
//        @JvmStatic
//        fun newInstance() =
//                DonateFragment().apply {
//                    arguments = Bundle().apply {}
//                }
//    }
/*
    private fun render(status: Boolean) {
        when (status) {
            true -> {
                view?.let {
                    //Uncomment this if you want to immediately return to Report
                    //findNavController().popBackStack()
                }
            }
            false -> Toast.makeText(context,getString(R.string.donationError), Toast.LENGTH_LONG).show()
        }
    }

 */

    fun setLoginButtonListener(layout: FragmentLaunchBinding) {
        layout.btnLogin.setOnClickListener {
            val action = LaunchFragmentDirections.actionLaunchFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }

    fun setCreateAccountButtonListener(layout: FragmentLaunchBinding) {
        layout.btnCreateAccount.setOnClickListener {
            val action = LaunchFragmentDirections.actionLaunchFragmentToCreateAccountFragment()
            findNavController().navigate(action)
        }
    }
/*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_donate, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    override fun onResume() {
        super.onResume()
        val reportViewModel = ViewModelProvider(this).get(ReportViewModel::class.java)
        reportViewModel.observableDonationsList.observe(viewLifecycleOwner, Observer {
            totalDonated = reportViewModel.observableDonationsList.value!!.sumBy { it.amount }
            fragBinding.progressBar.progress = totalDonated
            fragBinding.totalSoFar.text = "$$totalDonated"
        })
    }

 */
}