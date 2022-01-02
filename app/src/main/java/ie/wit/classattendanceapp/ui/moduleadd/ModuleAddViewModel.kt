package ie.wit.classattendanceapp.ui.moduleadd

import androidx.lifecycle.ViewModel
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerModules
import ie.wit.classattendanceapp.models.ModuleModel

class ModuleAddViewModel: ViewModel() {

    fun addModule(module: ModuleModel) {
        FirebaseDBManagerModules.createModule(module)
    }
}