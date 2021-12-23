package ie.wit.classattendanceapp.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class LectureModel(var id: Int =0,
                        var startTime: String ="",
                        var endTime: String ="",
                        var day: String="",
                        var location: String="",
                        var cancelMessage:String = ""):Parcelable
{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "startTime" to startTime,
            "endTime" to endTime,
            "day" to day,
            "location" to location,
            "cancelMessage" to cancelMessage
        )
    }
}
