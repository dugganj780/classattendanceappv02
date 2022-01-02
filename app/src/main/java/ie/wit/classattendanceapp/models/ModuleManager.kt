package ie.wit.classattendanceapp.models
import androidx.lifecycle.MutableLiveData
import java.util.*

fun generateARandomId(): Long {
    return Random().nextLong()
}

object ModuleManager : ModuleStore {

    val modules = ArrayList<ModuleModel>()
    val users = ArrayList<UserModel>()


    override fun findAll(moduleList: MutableLiveData<List<ModuleModel>>) {
        //return modules
    }

    override fun createModule(module: ModuleModel) {
      //  module.uid = generateARandomId()
        modules.add(module)
    }

    override fun findModuleById(uid: String, module: MutableLiveData<ModuleModel>){}

    override fun updateModule(module: ModuleModel){}

    override fun deleteModule(moduleId: String){}
/*
    override fun delete(module: ModuleModel) {
        modules.remove(module)
    }

    override fun findOne(id: Long) : ModuleModel? {
        var foundModule: ModuleModel? = modules.find { p -> p.id == id }
        return foundModule
    }

    override fun findLectures(id: Long) : List<LectureModel>{
        var foundModule: ModuleModel? = modules.find { p -> p.id == id }
        var lectures = foundModule!!.lectures
        return lectures
    }

    override fun updateLecture(module: ModuleModel, lecture: LectureModel) {
        var foundModule: ModuleModel? = modules.find { p -> p.id == module.id }

        if (foundModule != null) {
            var lectures = foundModule.lectures
            //var lecture = LectureModel(1,"","","","","")

            val iterator = lectures.listIterator()
            for (item in iterator) {
                if (item.id == lecture.id) {
                    item.startTime = lecture.startTime
                    item.endTime = lecture.endTime
                    item.day = lecture.day
                    item.location = lecture.location
                    item.cancelMessage = lecture.cancelMessage
                }
            }
        }

    }
*/

}