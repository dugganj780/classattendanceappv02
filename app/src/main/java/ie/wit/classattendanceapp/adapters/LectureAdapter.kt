package ie.wit.classattendanceapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.classattendanceapp.databinding.CardLectureBinding
import ie.wit.classattendanceapp.models.LectureModel

interface LectureListener {
    fun onLectureClick(lecture: LectureModel)
}

class LectureAdapter(private var lectures: List<LectureModel>, private val listener: LectureListener) :
    RecyclerView.Adapter<LectureAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardLectureBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val lecture = lectures[holder.adapterPosition]
        holder.bind(lecture, listener)
    }

    override fun getItemCount(): Int = lectures.size

    class MainHolder(private val binding : CardLectureBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(lecture: LectureModel, listener: LectureListener) {
            var lectureTimes = lecture.startTime +" to "+lecture.endTime

            binding.day.text = lecture.day
            binding.lectureTimes.text = lectureTimes
            binding.lectureLocation.text = lecture.location
            binding.cancelMessage.text = lecture.cancelMessage
            binding.root.setOnClickListener{listener.onLectureClick(lecture)}
        }
    }

}