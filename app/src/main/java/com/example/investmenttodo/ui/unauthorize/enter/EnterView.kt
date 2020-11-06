package com.example.investmenttodo.ui.unauthorize.enter

import com.example.investmenttodo.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface EnterView : BaseView {

    @AddToEndSingle
    fun changeEnableEnterNowButton(enable: Boolean)

    @Skip
    fun showAlertDialog(titleResId: Int, messageResId: Int)

    @AddToEndSingle
    fun showDrawerMenuScreen()

    @AddToEndSingle
    fun showProgress()

    @AddToEndSingle
    fun hideProgress()
}