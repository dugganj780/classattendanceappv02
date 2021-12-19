package ie.wit.classattendanceapp.models

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser

interface UserStore {
    fun findAllUsers(userList: MutableLiveData<List<UserModel>>)
    fun createUser(firebaseUser: MutableLiveData<FirebaseUser>)
    fun updateUser(email: String)
    fun findUserModules(email: String): List<ModuleModel>
    fun updateUserModules(email: String, modules:MutableList<ModuleModel>)
    fun addUserModule(email: String, module:ModuleModel)
}