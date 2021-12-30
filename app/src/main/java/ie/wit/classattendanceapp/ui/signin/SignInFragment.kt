package ie.wit.classattendanceapp.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import ie.wit.classattendanceapp.databinding.FragmentModuleBinding
import ie.wit.classattendanceapp.databinding.FragmentSignInBinding
import ie.wit.classattendanceapp.main.ClassAttendanceApp
import ie.wit.classattendanceapp.models.LectureModel
import ie.wit.classattendanceapp.models.ModuleModel
import ie.wit.classattendanceapp.models.SignInModel
import ie.wit.classattendanceapp.ui.module.ModuleFragmentArgs
import ie.wit.classattendanceapp.ui.module.ModuleViewModel
import timber.log.Timber

class SignInFragment: Fragment() {
    lateinit var app: ClassAttendanceApp
    lateinit var map: GoogleMap
    private val args by navArgs<ModuleFragmentArgs>()
    private var _fragBinding: FragmentSignInBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var signInViewModel: SignInViewModel
    var currentSignIn = SignInModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentSignInBinding.inflate(inflater, container, false)
        val root = fragBinding.root


        signInViewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        signInViewModel.observableSignIn.observe(viewLifecycleOwner, Observer {
                signIn ->
            signIn.let {
                Timber.i("Sign In is $signIn")
                currentSignIn = signIn
            }
        })

        fragBinding.mapView.onCreate(savedInstanceState)
        fragBinding.mapView.getMapAsync {
            map = it
            signInViewModel.doConfigureMap(map, currentSignIn)
        }
        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        fragBinding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        fragBinding.mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        fragBinding.mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        fragBinding.mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragBinding.mapView.onSaveInstanceState(outState)
    }
}