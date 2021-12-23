package ie.wit.classattendanceapp.models
import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import java.util.*




object UserManager : UserStore{
    var users = mutableListOf<UserModel>()

    override fun createUser(student: UserModel) {
        users.add(student)
    }

    //override fun findUserModules(userEmail: String, modules:MutableList<ModuleModel>){}
    override fun findById(uid: String, student: MutableLiveData<UserModel>){}

    override fun updateUser(student: UserModel) {
        /*
        var foundUser: UserModel? = users.find { p -> p.studentID == student.studentID }
        if (foundUser != null) {
            Timber.i("Found User is $foundUser")
            foundUser.modules = modules
            student.modules = modules
        }
        
         */
    }
/*
    override fun findAllUsers(): List<UserModel> {
        return users
    }



    override fun updateUser(student: UserModel) {
        var foundUser: UserModel? = users.find { p -> p.studentID == student.studentID }
        if (foundUser != null) {
            foundUser.firstName = student.firstName
            foundUser.surname = student.surname
            foundUser.studentID = student.studentID
            foundUser.password = student.password
        }
    }



    override fun addUserModule(student: UserModel, module:ModuleModel) {
        var foundUser: UserModel? = users.find { p -> p.studentID == student.studentID }
        if (foundUser != null) {
            student.modules.add(module)
            Timber.i("Found User is $foundUser")

        }
    }

 */
}