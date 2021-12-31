package ie.wit.classattendanceapp.models

import androidx.lifecycle.MutableLiveData

interface ModuleStore {
    fun findAll(moduleList: MutableLiveData<List<ModuleModel>>)
    fun createModule(module: ModuleModel)
    fun findModuleById(uid: String, module: MutableLiveData<ModuleModel>)
    fun updateModule(module: ModuleModel)
    /*
    fun delete(module: ModuleModel)
    fun findOne(id: Long): ModuleModel?
    fun findLectures(id:Long): List<LectureModel>
    fun updateLecture(module: ModuleModel, lecture: LectureModel)

     */
}