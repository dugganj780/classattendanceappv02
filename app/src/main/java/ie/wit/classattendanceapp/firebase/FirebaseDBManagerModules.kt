package ie.wit.classattendanceapp.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import ie.wit.classattendanceapp.models.ModuleModel
import ie.wit.classattendanceapp.models.ModuleStore
import ie.wit.classattendanceapp.models.UserModel
import ie.wit.classattendanceapp.models.UserStore
import timber.log.Timber

var moduleDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference


object FirebaseDBManagerModules : ModuleStore {

    override fun createModule(module: ModuleModel){
        Timber.i("Firebase DB Reference : $moduleDatabase")

        val key = moduleDatabase.child("modules").push().key
        if (key == null) {
            Timber.i("Firebase Error : Key Empty")
            return
        }
        module.uid = key
        val moduleValues = module.toMap()

        val childAdd = HashMap<String, Any>()
        childAdd["/modules/${key}"] = moduleValues

        moduleDatabase.updateChildren(childAdd)    }

    override fun findAll(moduleList: MutableLiveData<List<ModuleModel>>) {

        moduleDatabase.child("modules")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.i("Firebase Modules error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val localList = ArrayList<ModuleModel>()
                    val children = snapshot.children
                    children.forEach {
                        val module = it.getValue(ModuleModel::class.java)
                        localList.add(module!!)
                    }
                    moduleDatabase.child("modules")
                        .removeEventListener(this)

                    moduleList.value = localList
                }
            })
    }

    override fun findModuleById(uid: String, module: MutableLiveData<ModuleModel>){
        moduleDatabase.child("modules").child(uid).get().addOnSuccessListener {
            Timber.i("module uid being searched is $uid")
            module.value = it.getValue(ModuleModel::class.java)
            Timber.i("firebase Got value ${it.value}")
        }.addOnFailureListener{
            Timber.e("firebase Error getting data $it")
        }

    }

    override fun updateModule(module: ModuleModel){
        val moduleValues = module.toMap()

        val childUpdate : MutableMap<String, Any?> = HashMap()
        childUpdate["modules/${module.uid}"] = moduleValues

        database.updateChildren(childUpdate)
    }

    override fun deleteModule(moduleId: String) {

        val childDelete : MutableMap<String, Any?> = HashMap()
        childDelete["/modules/$moduleId"] = null

        database.updateChildren(childDelete)
    }

    /*
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
       val childUpdate: MutableMap<String, Any?> = HashMap()
       childUpdate["users/$uid/modules"] = modules
   }


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