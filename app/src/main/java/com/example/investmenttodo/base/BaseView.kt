package com.example.investmenttodo.base

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface BaseView : MvpView {

    @AddToEndSingle
    fun showKeyboard()

    @AddToEndSingle
    fun hideKeyboard()

}