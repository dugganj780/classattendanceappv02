package ie.wit.classattendanceapp.models

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser

interface UserStore {
    fun createUser(student: UserModel)
    //fun findUserModules(uid: String, modules: List<ModuleModel>)
    fun findById(uid: String, student: MutableLiveData<UserModel>)
    fun updateUser(student: UserModel)


    /*
    fun findAllUsers(userList: MutableLiveData<List<UserModel>>)
    fun updateUser(userEmail: String, student: UserModel)
    fun addUserModule(email: String, module:ModuleModel)

     */
}