package ie.wit.classattendanceapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(var studentID: Long =0, var firstName: String = "", var surname: String = "",
                        var password: String ="", var isAdmin:Boolean = false, var modules: MutableList<ModuleModel> = mutableListOf(ModuleModel())): Parcelable
