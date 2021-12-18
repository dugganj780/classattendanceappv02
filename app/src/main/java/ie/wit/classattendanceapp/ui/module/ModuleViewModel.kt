package ie.wit.classattendanceapp.ui.module

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.classattendanceapp.models.ModuleManager
import ie.wit.classattendanceapp.models.LectureModel

class ModuleViewModel : ViewModel() {
    private val lectures = MutableLiveData<List<LectureModel>>()

    val observableLectures: LiveData<List<LectureModel>>
        get() = lectures

    fun getLectures(id: Long){
        lectures.value = ModuleManager.findLectures(id)
    }
}