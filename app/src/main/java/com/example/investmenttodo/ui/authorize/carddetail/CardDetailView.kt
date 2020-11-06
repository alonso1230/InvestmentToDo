package com.example.investmenttodo.ui.authorize.carddetail

import com.example.investmenttodo.base.BaseView
import com.example.investmenttodo.dataclass.Card
import com.example.investmenttodo.dataclass.Comment
import com.example.investmenttodo.dataclass.User
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface CardDetailView : BaseView {

    @AddToEndSingle
    fun initView(card: Card, createUser: User, executorUser: User, status: String)

    @AddToEndSingle
    fun setStatusCardText(status: String)

    @Skip
    fun addComment(comment: Comment)

}