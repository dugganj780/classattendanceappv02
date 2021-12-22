package ie.wit.classattendanceapp.ui.modulelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerUsers
import ie.wit.classattendanceapp.models.ModuleManager
import ie.wit.classattendanceapp.models.ModuleModel
import ie.wit.classattendanceapp.models.UserManager
import ie.wit.classattendanceapp.models.UserModel
import timber.log.Timber

class ModuleListViewModel : ViewModel() {

    private val moduleList = MutableLiveData<List<ModuleModel>>()
    private val student = MutableLiveData<UserModel>()

   // var observableModuleList: LiveData<List<ModuleModel>>
        //get() = moduleList
        //set(value){moduleList.value = student.modules}



    fun getUserModules(studentId: Long) {
       // moduleList.value = UserManager.findUserModules(studentId)
    }


    fun getModules(){

    }
/*
    fun delete(id: String) {
        try {
            ModuleManager.delete(id)
            Timber.i("Retrofit Delete Success")
        }
        catch (e: Exception) {
            Timber.i("Retrofit Delete Error : $e.message")
        }
    }

 */
}