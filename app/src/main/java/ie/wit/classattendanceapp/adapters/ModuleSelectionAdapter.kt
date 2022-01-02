package ie.wit.classattendanceapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import ie.wit.classattendanceapp.databinding.CardModuleSelectionBinding
import ie.wit.classattendanceapp.models.ModuleModel
import ie.wit.classattendanceapp.models.UserModel
import timber.log.Timber
import timber.log.Timber.i


interface ModuleSelectionListener {
    fun onModuleAdd(module: ModuleModel)
    fun onModuleRemove(module: ModuleModel)


}

class ModuleSelectionAdapter constructor(private var modules: List<ModuleModel>, private val listener: ModuleSelectionListener) :
    RecyclerView.Adapter<ModuleSelectionAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardModuleSelectionBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val module = modules[holder.adapterPosition]

        holder.bind(module, listener)

    }

    override fun getItemCount(): Int = modules.size

    class MainHolder(private val binding : CardModuleSelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(module: ModuleModel, listener: ModuleSelectionListener) {
            binding.moduleCode.text = module.moduleCode
            binding.addModule.setOnClickListener{listener.onModuleAdd(module)}
            binding.removeModule.setOnClickListener{listener.onModuleRemove(module)}
        }
    }
}