package ie.wit.classattendanceapp.ui.attendance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerModules
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerSignIns
import ie.wit.classattendanceapp.models.ModuleModel
import ie.wit.classattendanceapp.models.SignInModel
import timber.log.Timber
import java.lang.Exception

class AttendanceViewModel : ViewModel() {

    private val moduleSignIns = MutableLiveData<List<SignInModel>>()
    private val module = MutableLiveData<ModuleModel>()

    var observableModule: LiveData<ModuleModel>
        get() = module
        set(value){module.value = value.value}

    var observableModuleSignInList: LiveData<List<SignInModel>>
        get() = moduleSignIns
        set(value){moduleSignIns.value = value.value}

    fun getModule(uid: String){
        Timber.i("$uid")
        try {
            FirebaseDBManagerModules.findModuleById(uid, module)
            Timber.i("Detail getStudent() Success : ${
                module.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Detail getStudent() Error : $e.message")
        }
    }


    fun getModuleSignIns(moduleId: String){
        Timber.i("$moduleId")
        try {
            FirebaseDBManagerSignIns.moduleSignIns(moduleId, moduleSignIns)
            Timber.i("Detail getStudent() Success : ${
                moduleSignIns.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Detail getStudent() Error : $e.message")
        }
    }
}