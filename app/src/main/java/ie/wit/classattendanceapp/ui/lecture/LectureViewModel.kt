package ie.wit.classattendanceapp.ui.lecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerModules
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerSignIns
import ie.wit.classattendanceapp.firebase.FirebaseDBManagerUsers
import ie.wit.classattendanceapp.models.LectureModel
import ie.wit.classattendanceapp.models.ModuleModel
import ie.wit.classattendanceapp.models.SignInModel
import ie.wit.classattendanceapp.models.UserModel
import timber.log.Timber
import java.lang.Exception

class LectureViewModel : ViewModel() {

    private val module = MutableLiveData<ModuleModel>()
    private val student = MutableLiveData<UserModel>()



    var observableModule: LiveData<ModuleModel>
        get() = module
        set(value){module.value = value.value}

    var observableStudent: LiveData<UserModel>
        get() = student
        set(value){student.value = value.value}

    /*
    fun getLecture(id: Int) {
        lecture.value = LectureManager.findById(id)
    }
     */

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

    fun updateModule(module: ModuleModel){
        FirebaseDBManagerModules.updateModule(module)
    }

    fun getStudent(uid:String) {
        Timber.i("$uid")
        try {
            FirebaseDBManagerUsers.findById(uid, student)
            Timber.i("Detail getStudent() Success : ${
                student.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Detail getStudent() Error : $e.message")
        }
    }

    fun getLecture(module: ModuleModel, lectureId:String):LectureModel {
        var foundLecture = LectureModel()
        val iterator = module.lectures.listIterator()
        for (lecture in iterator) {
            if (lectureId == lecture.id) {
                foundLecture = lecture
            }
        }
        return foundLecture
    }

    fun addSignIn(signIn: SignInModel){
        FirebaseDBManagerSignIns.createSignIn(signIn)
    }

}