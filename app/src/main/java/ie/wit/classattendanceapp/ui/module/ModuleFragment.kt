package ie.wit.classattendanceapp.ui.module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.classattendanceapp.adapters.LectureAdapter
import ie.wit.classattendanceapp.adapters.LectureListener
import ie.wit.classattendanceapp.databinding.FragmentModuleBinding
import ie.wit.classattendanceapp.databinding.FragmentSlideshowBinding
import ie.wit.classattendanceapp.models.LectureModel
import ie.wit.classattendanceapp.models.ModuleModel

class ModuleFragment : Fragment(), LectureListener {

    private lateinit var moduleViewModel: ModuleViewModel
    private val args by navArgs<ModuleFragmentArgs>()
    private var _fragBinding: FragmentModuleBinding? = null
    private val fragBinding get() = _fragBinding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentModuleBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        moduleViewModel = ViewModelProvider(this).get(ModuleViewModel::class.java)
        moduleViewModel.observableModule.observe(viewLifecycleOwner, Observer { render() })
        return root
    }

    private fun render(lectures: ArrayList<LectureModel>) {
        // fragBinding.editAmount.setText(donation.amount.toString())
        // fragBinding.editPaymenttype.text = donation.paymentmethod
        fragBinding.recyclerView.adapter = LectureAdapter(lectures, this)
    }

    override fun onResume() {
        super.onResume()
        moduleViewModel.getModule(args.id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}