package ie.wit.classattendanceapp.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ie.wit.classattendanceapp.databinding.FragmentSignInBinding
import ie.wit.classattendanceapp.main.ClassAttendanceApp
import ie.wit.classattendanceapp.models.SignInModel
import timber.log.Timber

class SignInFragment: Fragment() {
    lateinit var app: ClassAttendanceApp
    lateinit var map: GoogleMap
    private val args by navArgs<SignInFragmentArgs>()
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
        fragBinding.mapView.onCreate(savedInstanceState)


        signInViewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        signInViewModel.observableSignIn.observe(viewLifecycleOwner, Observer {
                signIn ->
            signIn.let {
                Timber.i("Sign In is $signIn")
                currentSignIn = signIn
                render(signIn)

            }
        })

        Timber.i("Current Sign In is $currentSignIn")

        return root
    }

    private fun render(signIn:SignInModel){
        fragBinding.mapView.getMapAsync {
            map = it
            Timber.i("Configure map lat in getMapAsync is ${currentSignIn.lat}")
            doConfigureMap(map, signIn)
        }
        val name:String = signIn.firstName+" "+signIn.surname
        var status:String =""
        if (signIn.inPerson){
            status="In Person"
        }

        if(signIn.live){
            status = "Live Online"
        }

        if (signIn.recording){
            status = "Recording"
        }
        fragBinding.lectureDay.text=signIn.day
        fragBinding.startTime.text=signIn.startTime
        fragBinding.signedIn.text=signIn.signTime
        fragBinding.module.text=signIn.moduleCode
        fragBinding.name.text=name
        fragBinding.viewMethod.text=status
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
        signInViewModel.getSignIn(args.signInId)
        fragBinding.mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        signInViewModel.getSignIn(args.signInId)
        //fragBinding.mapView.onStart()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragBinding.mapView.onSaveInstanceState(outState)
    }

    fun doConfigureMap(m: GoogleMap, signIn: SignInModel) {
        map = m
        Timber.i("Configure map lat is ${signIn.lat}")
        locationUpdate(signIn)
    }

    fun locationUpdate(signIn: SignInModel) {
        //currentSignIn.lat = lat
        //currentSignIn.lng = lng
        signIn.zoom = 15f
        map?.clear()
        map?.uiSettings?.setZoomControlsEnabled(true)
        val options = MarkerOptions().title(signIn.surname + ", " + signIn.firstName).position(
            LatLng(signIn.lat, signIn.lng)
        )
        map?.addMarker(options)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(signIn.lat, signIn.lng), signIn.zoom))
        //view?.showPlacemark(signIn)
    }
}