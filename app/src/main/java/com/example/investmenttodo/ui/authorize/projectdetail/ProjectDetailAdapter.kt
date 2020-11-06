package com.example.investmenttodo.ui.authorize.projectdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.investmenttodo.R
import com.example.investmenttodo.dataclass.Card
import com.example.investmenttodo.manager.DataManager
import com.example.investmenttodo.util.view.CircleView
import kotlinx.android.synthetic.main.item_card.view.*

class ProjectDetailAdapter : RecyclerView.Adapter<ProjectDetailAdapter.ViewHolder>() {

    private var cardList: MutableList<Card> = ArrayList()
    var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = cardList[position]
        holder.tvProjectName.text = project.name
        holder.tvCardDescription.text = project.description
        holder.cvCardUser.setFirstLetter(DataManager.getUserByName(project.executorMember).name)
        holder.cvCardUser.setCircleColor(DataManager.getUserByName(project.executorMember).backgroundColor)
        if (project.comments.isNullOrEmpty()) {
            holder.tvCardCommentCount.visibility = View.GONE
        } else {
            holder.tvCardCommentCount.text = project.comments?.size.toString()
            holder.tvCardCommentCount.visibility = View.VISIBLE
        }
    }

    fun setData(dataList: MutableList<Card>) {
        cardList = dataList
        notifyDataSetChanged()
    }

    override fun getItemCount() = cardList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProjectName: TextView = itemView.tvCardName
        val tvCardDescription: TextView = itemView.tvCardDescription
        val cvCardUser: CircleView = itemView.cvCardUser
        val tvCardCommentCount: TextView = itemView.tvCardCommentCount

        init {
            itemView.setOnClickListener { onItemClickListener?.invoke(adapterPosition) }
        }
    }
}