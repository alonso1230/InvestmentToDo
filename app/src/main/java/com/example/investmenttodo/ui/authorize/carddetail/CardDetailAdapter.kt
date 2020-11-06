package com.example.investmenttodo.ui.authorize.carddetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.investmenttodo.R
import com.example.investmenttodo.app.Constants
import com.example.investmenttodo.dataclass.Comment
import com.example.investmenttodo.manager.DataManager
import com.example.investmenttodo.util.extension.toDate
import com.example.investmenttodo.util.view.CircleView
import kotlinx.android.synthetic.main.item_comment.view.*

class CardDetailAdapter : RecyclerView.Adapter<CardDetailAdapter.ViewHolder>() {

    private var dataList: MutableList<Comment> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = dataList[position]
        val user = DataManager.getUserByName(comment.author)
        holder.cvCommentUser.setFirstLetter(comment.author)
        holder.cvCommentUser.setCircleColor(user.backgroundColor)
        holder.tvCommentUserName.text = comment.author
        holder.tvComment.text = comment.text
        holder.tvCommentDate.text = comment.date.toDate(Constants.DATE_FORMAT)
    }

    fun setData(dataList: MutableList<Comment>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCommentUser: CircleView = itemView.cvCommentUser
        val tvCommentUserName: TextView = itemView.tvCommentUserName
        val tvComment: TextView = itemView.tvComment
        val tvCommentDate: TextView = itemView.tvCommentDate
    }
}