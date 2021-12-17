package ie.wit.classattendanceapp.models

interface SignInStore {
    fun findAll(): List<SignInModel>
    fun create(signIn: SignInModel)
    fun moduleSignIns(module: ModuleModel, lecture: LectureModel): List<SignInModel>
}