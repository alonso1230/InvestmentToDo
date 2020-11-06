package com.example.investmenttodo.ui.main

import com.example.investmenttodo.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : BaseView {
    @AddToEndSingle
    fun showDrawerMenuScreen()
}