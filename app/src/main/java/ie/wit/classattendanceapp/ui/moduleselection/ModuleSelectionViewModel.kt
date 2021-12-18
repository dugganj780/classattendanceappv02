package ie.wit.classattendanceapp.ui.moduleselection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.classattendanceapp.models.ModuleManager
import ie.wit.classattendanceapp.models.ModuleModel
import timber.log.Timber

class ModuleSelectionViewModel: ViewModel() {
    private val moduleList = MutableLiveData<List<ModuleModel>>()

    val observableModuleList: LiveData<List<ModuleModel>>
        get() = moduleList

    init {
        load()
    }

    fun load() {
        try {
            ModuleManager.findAll()
            Timber.i("Retrofit Success : $moduleList.value")
        }
        catch (e: Exception) {
            Timber.i("Retrofit Error : $e.message")
        }
    }
}