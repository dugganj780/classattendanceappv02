package ie.wit.classattendanceapp.models

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
data class SignInModel(var uid:String="",
                       var studentID: Long = 0,
                       var firstName:String ="",
                       var surname:String ="",
                       var moduleCode: String = "",
                       var moduleId: String ="",
                       var day:String = "",
                       var startTime: String = "",
                       var signTime: String = "",
                       var lat: Double = 0.0,
                       var lng: Double = 0.0,
                       var zoom: Float = 0f,
                       var inPerson: Boolean = false,
                       var live: Boolean = false,
                       var recording: Boolean = true)

{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "studentID" to studentID,
            "firstName" to firstName,
            "surname" to surname,
            "moduleCode" to moduleCode,
            "moduleId" to moduleId,
            "day" to day,
            "startTime" to startTime,
            "signTime" to signTime,
            "lat" to lat,
            "lng" to lng,
            "zoom" to zoom,
            "inPerson" to inPerson,
            "live" to live,
            "recording" to recording
        )
    }
}