package ie.wit.classattendanceapp.ui.module

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerModules
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerUsers
import ie.wit.classattendanceapp.models.ModuleManager
import ie.wit.classattendanceapp.models.LectureModel
import ie.wit.classattendanceapp.models.ModuleModel
import timber.log.Timber
import java.lang.Exception

class ModuleViewModel : ViewModel() {
    private val module = MutableLiveData<ModuleModel>()

    var observableModule: LiveData<ModuleModel>
        get() = module
        set(value){module.value = value.value}

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

    fun deleteModule(moduleId: String){
        try {
            FirebaseDBManagerModules.deleteModule(moduleId)
            Timber.i("Module Deleted")
        }
        catch (e: Exception) {
            Timber.i("Could not delete")
        }
    }
}