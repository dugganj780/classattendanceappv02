package ie.wit.classattendanceapp.models

import androidx.lifecycle.MutableLiveData

interface SignInStore {
    fun findAll(signIns: MutableLiveData<List<SignInModel>>)
    fun createSignIn(signIn: SignInModel)
    fun moduleSignIns(moduleId: String,signIns: MutableLiveData<List<SignInModel>>)
    fun findSignInById(uid: String, signIn: MutableLiveData<SignInModel>)
}