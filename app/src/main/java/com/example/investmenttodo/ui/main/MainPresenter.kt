package com.example.investmenttodo.ui.main

import com.example.investmenttodo.app.App
import com.example.investmenttodo.base.BasePresenter
import moxy.InjectViewState

@InjectViewState
class MainPresenter : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        if (App.instance.prefs.userName.isNotEmpty()) {
            viewState.showDrawerMenuScreen()
        }
    }

}