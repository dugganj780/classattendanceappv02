package ie.wit.classattendanceapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LectureModel(var id: Int =0,
                        var startTime: String ="",
                        var endTime: String ="",
                        var day: String="",
                        var location: String="",
                        var cancelMessage:String = ""):Parcelable {
}
