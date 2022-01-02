package ie.wit.classattendanceapp.ui.modulelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.classattendanceapp.models.ModuleModel
import ie.wit.classattendanceapp.models.UserModel

class ModuleListViewModel : ViewModel() {

    private val moduleList = MutableLiveData<List<ModuleModel>>()
    private val student = MutableLiveData<UserModel>()


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