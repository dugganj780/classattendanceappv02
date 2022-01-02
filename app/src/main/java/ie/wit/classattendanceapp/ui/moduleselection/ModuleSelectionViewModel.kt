package ie.wit.classattendanceapp.ui.moduleselection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerModules
import ie.wit.classattendanceapp.models.ModuleModel
import timber.log.Timber
import java.lang.Exception

class ModuleSelectionViewModel: ViewModel() {
    private val moduleList = MutableLiveData<List<ModuleModel>>()

    val observableModuleList: LiveData<List<ModuleModel>>
        get() = moduleList

    init {
        load()
    }

    fun load() {
        try {
            FirebaseDBManagerModules.findAll(moduleList)
            Timber.i("Report Load Success : ${moduleList.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Report Load Error : $e.message")
        }
    }


}