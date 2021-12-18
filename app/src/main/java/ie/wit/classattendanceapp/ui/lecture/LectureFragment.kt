package ie.wit.classattendanceapp.ui.lecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ie.wit.classattendanceapp.databinding.FragmentLectureBinding
import ie.wit.classattendanceapp.databinding.HomeBinding
import ie.wit.classattendanceapp.models.LectureModel

class LectureFragment : Fragment() {

    private lateinit var lectureViewModel: LectureViewModel
    private val args by navArgs<LectureFragmentArgs>()
    private var _fragBinding: FragmentLectureBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentLectureBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        lectureViewModel = ViewModelProvider(this).get(LectureViewModel::class.java)
        lectureViewModel.observableLecture.observe(viewLifecycleOwner, Observer { render() })
        return root
        }


private fun render() {
    // fragBinding.editAmount.setText(donation.amount.toString())
    // fragBinding.editPaymenttype.text = donation.paymentmethod
    fragBinding.lecturevm = lectureViewModel
}

override fun onResume() {
    super.onResume()
    fragBinding.lecturevm = lectureViewModel
}

override fun onDestroyView() {
    super.onDestroyView()
    _fragBinding = null
}
}