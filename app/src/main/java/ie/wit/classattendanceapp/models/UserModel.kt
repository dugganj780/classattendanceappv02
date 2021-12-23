package ie.wit.classattendanceapp.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@IgnoreExtraProperties
@Parcelize
data class UserModel(var uid: String = "",
                     var key: String = "",
                     var studentID: Long =0,
                     var email: String = "",
                     var firstName: String = "",
                     var surname: String = "",
                     var password: String ="",
                     var isAdmin:Boolean = false,
                     var modules: MutableList<ModuleModel> = mutableListOf<ModuleModel>())
                     : Parcelable
{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "key" to key,
            "studentID" to studentID,
            "email" to email,
            "firstName" to firstName,
            "surname" to surname,
            "password" to password,
            "isAdmin" to isAdmin,
            "modules" to modules
        )
    }
}
