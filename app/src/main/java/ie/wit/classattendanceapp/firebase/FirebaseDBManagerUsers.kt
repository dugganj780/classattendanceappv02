package ie.wit.classattendanceapp.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ie.wit.classattendanceapp.models.ModuleModel
import ie.wit.classattendanceapp.models.UserModel
import ie.wit.classattendanceapp.models.UserStore
import timber.log.Timber

var database: DatabaseReference = FirebaseDatabase.getInstance().reference


object FirebaseDBManagerUsers : UserStore {

    override fun createUser(student: UserModel){
        Timber.i("Firebase DB Reference : $database")

        val key = database.child("users").child(student.uid)
        if (key == null) {
            Timber.i("Firebase Error : Key Empty")
            return
        }
        val userValues = student.toMap()

        val childAdd = HashMap<String, Any>()
        childAdd["/users/${student.uid}"] = userValues

        database.updateChildren(childAdd)    }

    override fun findById(uid: String, student: MutableLiveData<UserModel>){
        database.child("users").child(uid).get().addOnSuccessListener {
                student.value = it.getValue(UserModel::class.java)
                /*
                if (student.value == null){
                    student.value = UserModel()
                }
                 */
                Timber.i("firebase Got value ${it.value}")
            }.addOnFailureListener{
                Timber.e("firebase Error getting data $it")
            }

    }

    override fun updateUserModules(uid: String, modules:MutableList<ModuleModel>){
        val iterator = modules.listIterator()
        for (module in iterator) {
            Timber.i("$module")
            val moduleValues = module.toMap()
            val childUpdate: MutableMap<String, Any?> = HashMap()
            childUpdate["users/$uid/modules/${module.uid}"] = moduleValues
        }
    }

    /*
        override fun findAllUsers(userList: MutableLiveData<List<UserModel>>) {
        TODO("Not yet implemented")
    }


    override fun updateUser(firebaseUser: MutableLiveData<FirebaseUser>) {
        TODO("Not yet implemented")
    }

    override fun create(firebaseUser: MutableLiveData<FirebaseUser>, donation: DonationModel) {
        TODO("Not yet implemented")
    }

    override fun delete(userid: String, donationid: String) {
        TODO("Not yet implemented")
    }

    override fun update(userid: String, donationid: String, donation: DonationModel) {
        TODO("Not yet implemented")
    }

     */
}