package ie.wit.classattendanceapp.ui.lecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.wit.classattendanceapp.models.LectureModel

class LectureViewModel : ViewModel() {

    private val lecture = MutableLiveData<LectureModel>()

    val observableLecture: LiveData<LectureModel>
        get() = lecture

    /*
    fun getLecture(id: Int) {
        lecture.value = LectureManager.findById(id)
    }
     */
}