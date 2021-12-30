package ie.wit.classattendanceapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.classattendanceapp.databinding.CardSignInBinding
import ie.wit.classattendanceapp.models.SignInModel

interface SignInListener{
    fun onSignInClick(signIn: SignInModel)
}

class SignInAdapter(private var attendance: List<SignInModel>, private val listener: SignInListener) :
    RecyclerView.Adapter<SignInAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardSignInBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val signIn = attendance[holder.adapterPosition]
        holder.bind(signIn,listener)

    }

    override fun getItemCount(): Int = attendance.size

    class MainHolder(private val binding : CardSignInBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var status:String = ""

        fun bind(signIn: SignInModel, listener: SignInListener) {
            if (signIn.inPerson){
                status="In Person"
            }

            if(signIn.live){
                status = "Live Online"
            }

            if (signIn.recording){
                status = "Recording"
            }

            binding.moduleCode.text = signIn.moduleCode
            binding.day.text = signIn.day
            binding.surname.text = signIn.surname
            binding.firstName.text = signIn.firstName
            binding.startTime.text = signIn.startTime
            binding.signInTime.text = signIn.signTime
            binding.status.text = status
            binding.root.setOnClickListener{listener.onSignInClick(signIn)}
        }
    }

}