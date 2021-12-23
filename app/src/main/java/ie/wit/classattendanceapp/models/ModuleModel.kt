package ie.wit.classattendanceapp.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@IgnoreExtraProperties
@Parcelize
data class ModuleModel(var uid: String = "",
                       var moduleCode: String ="",
                       var title: String="",
                       var lectures: MutableList<LectureModel> = mutableListOf<LectureModel>())
    : Parcelable
{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "moduleCode" to moduleCode,
            "title" to title,
            "lectures" to lectures
        )
    }
}