package com.example.investmenttodo.ui.authorize.projectdetail

import com.example.investmenttodo.base.BaseView
import com.example.investmenttodo.dataclass.Card
import com.example.investmenttodo.dataclass.Project
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface ProjectDetailView : BaseView {

    @AddToEndSingle
    fun initView(project: Project)

    @AddToEndSingle
    fun setData(cardList: MutableList<Card>)

    @Skip
    fun showCardDetailScreen(card: Card)

    @Skip
    fun showEditTextDialog()

    @AddToEndSingle
    fun setTitleScreen(title: String)

}