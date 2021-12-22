package ie.wit.classattendanceapp.models

import timber.log.Timber
import java.util.*

class SignInManager: SignInStore {
    var modules = mutableListOf<ModuleModel>()
    var attendance = mutableListOf<SignInModel>()

    override fun findAll(): MutableList<SignInModel> {
        return attendance
    }

    override fun create(signIn: SignInModel) {
        attendance.add(signIn)
    }

    override fun moduleSignIns(module: ModuleModel,lecture: LectureModel): MutableList<SignInModel> {
        var foundModule: ModuleModel? = modules.find { p -> p.uid == module.uid }
        var moduleAttendance = mutableListOf<SignInModel>()
        var moduleAttendanceByDay = mutableListOf<SignInModel>()

        if (foundModule != null) {

            //var lecture = LectureModel(1,"","","","","")

            val iterator = attendance.listIterator()
            for (item in iterator) {
                if (item.moduleCode == foundModule.moduleCode) {
                    Timber.i("$foundModule")
                    moduleAttendance.add(item.copy())
                }
            }
            val dayIterator = moduleAttendance.listIterator()
            for(foundLecture in dayIterator) {
                if (foundLecture.day == lecture.day) {
                    Timber.i("$foundLecture")
                    moduleAttendanceByDay.add(foundLecture.copy())
                }
            }

        }
        Timber.i("$moduleAttendance")
        return moduleAttendanceByDay
    }
}