package ie.wit.classattendanceapp.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import ie.wit.classattendanceapp.models.*
import timber.log.Timber

var signInDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference


object FirebaseDBManagerSignIns : SignInStore {

    override fun createSignIn(signIn: SignInModel){
        Timber.i("Firebase DB Reference : $signInDatabase")

        val key = signInDatabase.child("signIns").push().key
        if (key == null) {
            Timber.i("Firebase Error : Key Empty")
            return
        }
        signIn.uid = key
        val signInValues = signIn.toMap()

        val childAdd = HashMap<String, Any>()
        childAdd["/signIns/${key}"] = signInValues

        signInDatabase.updateChildren(childAdd)    }

    override fun findAll(signIns: MutableLiveData<List<SignInModel>>) {

        signInDatabase.child("signIns")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.i("Firebase Modules error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val localList = ArrayList<SignInModel>()
                    val children = snapshot.children
                    children.forEach {
                        val signIn = it.getValue(SignInModel::class.java)
                        localList.add(signIn!!)
                    }
                    signInDatabase.child("signIns")
                        .removeEventListener(this)

                    signIns.value = localList
                }
            })
    }

    override fun findSignInById(uid: String, signIn: MutableLiveData<SignInModel>){
        signInDatabase.child("signIns").child(uid).get().addOnSuccessListener {
            Timber.i("signIn uid being searched is $uid")
            signIn.value = it.getValue(SignInModel::class.java)
            Timber.i("firebase Got value ${it.value}")
        }.addOnFailureListener{
            Timber.e("firebase Error getting data $it")
        }
    }

    override fun moduleSignIns(moduleId: String,signIns: MutableLiveData<List<SignInModel>>) {

        signInDatabase.child("signIns")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.i("Firebase Modules error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val localList = ArrayList<SignInModel>()
                    val children = snapshot.children
                    children.forEach {
                        val signIn = it.getValue(SignInModel::class.java)
                        localList.add(signIn!!)
                    }
                    signInDatabase.child("signIns")
                        .removeEventListener(this)

                    val moduleSignIns = ArrayList<SignInModel>()
                    val iterator = localList.iterator()
                    for (item in iterator){
                        if(item.moduleId == moduleId){
                            moduleSignIns.add(item)
                        }
                    }
                    signIns.value = moduleSignIns
                }
            })
    }
}