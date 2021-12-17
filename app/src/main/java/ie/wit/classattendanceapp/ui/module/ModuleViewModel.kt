package ie.wit.classattendanceapp.ui.module

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.classattendanceapp.models.ModuleManager
import ie.wit.classattendanceapp.models.ModuleModel

class ModuleViewModel : ViewModel() {
    private val module = MutableLiveData<ModuleModel>()

    val observableModule: LiveData<ModuleModel>
        get() = module

    fun getModule(id: Long){
        module.value = ModuleManager.findOne(id)
    }

}