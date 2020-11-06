package com.example.investmenttodo.ui.authorize.carddetail

import com.example.investmenttodo.app.App
import com.example.investmenttodo.app.Constants
import com.example.investmenttodo.base.BasePresenter
import com.example.investmenttodo.dataclass.Card
import com.example.investmenttodo.dataclass.Comment
import com.example.investmenttodo.manager.DataManager
import moxy.InjectViewState

@InjectViewState
class CardDetailPresenter : BasePresenter<CardDetailView>() {

    private lateinit var card: Card

    override fun onFirstViewAttach() {
        card = DataManager.getCurrentCard()!!
        val createUser = DataManager.getUserByName(card.createMember)
        val executorUser = DataManager.getUserByName(card.executorMember)
        val statusCard = DataManager.getStatusCurrentCard()
        viewState.initView(card, createUser, executorUser, statusCard)
    }

    fun onOpenClicked() {
        DataManager.setStatusCurrentCard(Constants.OPEN)
        viewState.setStatusCardText(Constants.OPEN)
    }

    fun onInProgressClicked() {
        DataManager.setStatusCurrentCard(Constants.IN_PROGRESS)
        viewState.setStatusCardText(Constants.IN_PROGRESS)
    }

    fun onResolveClicked() {
        DataManager.setStatusCurrentCard(Constants.RESOLVE)
        viewState.setStatusCardText(Constants.RESOLVE)
    }

    fun onAddCommentClicked(commentText: String) {
        if (commentText.isNotEmpty()) {
            val comment = Comment(commentText, System.currentTimeMillis().toString(), App.instance.prefs.userName)
            DataManager.addCommentToCurrentCard(comment)
            viewState.addComment(comment)
        }
    }
}