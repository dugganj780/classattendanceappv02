package ie.wit.classattendanceapp.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerModules
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerSignIns
import ie.wit.classattendanceapp.models.ModuleModel
import ie.wit.classattendanceapp.models.SignInModel
import timber.log.Timber
import java.lang.Exception
import kotlin.math.sign

class SignInViewModel: ViewModel() {
    private val signIn = MutableLiveData<SignInModel>()
    var map: GoogleMap? = null



    var observableSignIn: LiveData<SignInModel>
        get() = signIn
        set(value){signIn.value = value.value}

    fun getSignIn(uid: String){
        Timber.i("$uid")
        try {
            FirebaseDBManagerSignIns.findSignInById(uid, signIn)
            Timber.i("Detail getSignIn() Success : ${
                signIn.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Detail getSignIn() Error : $e.message")
        }
    }

    fun doConfigureMap(m: GoogleMap, signIn: SignInModel) {
        map = m
        locationUpdate(signIn, signIn.lat, signIn.lng)
    }

    fun locationUpdate(signIn: SignInModel, lat: Double, lng: Double) {
        signIn.lat = lat
        signIn.lng = lng
        signIn.zoom = 15f
        map?.clear()
        map?.uiSettings?.setZoomControlsEnabled(true)
        val options = MarkerOptions().title(signIn.surname + ", " + signIn.firstName).position(LatLng(signIn.lat, signIn.lng))
        map?.addMarker(options)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(signIn.lat, signIn.lng), signIn.zoom))
        //view?.showPlacemark(signIn)
    }
}