package ie.wit.classattendanceapp.ui.launch

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import ie.wit.classattendanceapp.databinding.CreateAccountBinding
import ie.wit.classattendanceapp.databinding.LaunchBinding
import ie.wit.classattendanceapp.databinding.LoginBinding
import ie.wit.classattendanceapp.ui.createaccount.CreateAccount
import ie.wit.classattendanceapp.ui.createaccount.CreateAccountViewModel
import ie.wit.classattendanceapp.ui.login.Login

class Launch: AppCompatActivity() {
    private lateinit var launchViewModel : LaunchViewModel
    private lateinit var launchBinding : LaunchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchBinding = LaunchBinding.inflate(layoutInflater)
        setContentView(launchBinding.root)


        launchBinding.btnLogin.setOnClickListener {
            val launcherIntent = Intent(this, Login::class.java)
            startActivityForResult(launcherIntent, 0)

        }

        launchBinding.btnCreateAccount.setOnClickListener {
            val launcherIntent = Intent(this, CreateAccount::class.java)
            startActivityForResult(launcherIntent, 0)


        }
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

    fun setLoginButtonListener() {
        launchBinding.btnLogin.setOnClickListener {
            val launcherIntent = Intent(this, Login::class.java)

        }
    }

    fun setCreateAccountButtonListener() {
        launchBinding.btnCreateAccount.setOnClickListener {
            val launcherIntent = Intent(this, CreateAccount::class.java)

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,"Click again to Close App...", Toast.LENGTH_LONG).show()
        finish()
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