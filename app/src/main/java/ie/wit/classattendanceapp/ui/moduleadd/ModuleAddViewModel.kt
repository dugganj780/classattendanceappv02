package ie.wit.classattendanceapp.ui.moduleadd

import androidx.lifecycle.ViewModel
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerModules
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerUsers
import ie.wit.classattendanceapp.models.ModuleModel
import ie.wit.classattendanceapp.models.UserModel

class ModuleAddViewModel: ViewModel() {





    fun addModule(module: ModuleModel) {
        FirebaseDBManagerModules.createModule(module)
    }
}