package com.example.investmenttodo.ui.authorize.projects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.investmenttodo.R
import com.example.investmenttodo.dataclass.Project
import kotlinx.android.synthetic.main.item_project.view.*

class ProjectsAdapter : RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {

    private var projectsList: MutableList<Project> = ArrayList()
    var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projectsList[position]
        holder.tvProjectName.text = project.name
        holder.pbProjectItem.progress = getProgressPercent(project)
    }

    private fun getProgressPercent(project: Project): Int {
        val totalSize = project.openCards.size + project.inProgressCards.size + project.resolveCards.size
        if (totalSize == 0) return totalSize
        return ((project.resolveCards.size * 100 + project.inProgressCards.size * 50) / totalSize)
    }

    fun setData(dataList: MutableList<Project>) {
        projectsList = dataList
        notifyDataSetChanged()
    }

    override fun getItemCount() = projectsList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProjectName: TextView = itemView.tvProjectName
        val pbProjectItem: ProgressBar = itemView.pbProjectItem

        init {
            itemView.setOnClickListener { onItemClickListener?.invoke(adapterPosition) }
        }
    }
}