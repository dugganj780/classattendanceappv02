package ie.wit.classattendanceapp.models
import timber.log.Timber
import java.util.*




class UserManager : UserStore{
    var users = mutableListOf<UserModel>()

    override fun findAllUsers(): List<UserModel> {
        return users
    }

    override fun findUserModules(student: UserModel): List<ModuleModel> {
        var modules: List<ModuleModel> = mutableListOf(ModuleModel())
        var foundUser: UserModel? = users.find { p -> p.studentID == student.studentID }
        if (foundUser != null) {
            modules = student.modules
        }
        return modules
    }

    override fun createUser(student: UserModel) {
        users.add(student)
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

    override fun updateUserModules(student: UserModel, modules:MutableList<ModuleModel>) {
        var foundUser: UserModel? = users.find { p -> p.studentID == student.studentID }
        if (foundUser != null) {
            Timber.i("Found User is $foundUser")
            foundUser.modules = modules
            student.modules = modules
        }
    }

    override fun addUserModule(student: UserModel, module:ModuleModel) {
        var foundUser: UserModel? = users.find { p -> p.studentID == student.studentID }
        if (foundUser != null) {
            student.modules.add(module)
            Timber.i("Found User is $foundUser")

        }
    }
}