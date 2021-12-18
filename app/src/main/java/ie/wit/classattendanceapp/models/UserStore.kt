package ie.wit.classattendanceapp.models

interface UserStore {
    fun findAllUsers():List<UserModel>
    fun createUser(student:UserModel)
    fun updateUser(student:UserModel)
    fun findUserModules(studentID: Long): List<ModuleModel>
    fun updateUserModules(student: UserModel, modules:MutableList<ModuleModel>)
    fun addUserModule(student: UserModel, module:ModuleModel)
}