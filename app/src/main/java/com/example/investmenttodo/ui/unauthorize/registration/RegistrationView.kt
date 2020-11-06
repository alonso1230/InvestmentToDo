package com.example.investmenttodo.ui.unauthorize.registration

import com.example.investmenttodo.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface RegistrationView : BaseView {

    @AddToEndSingle
    fun changeEnableRegistrationNowButton(enable: Boolean)

    @Skip
    fun showLongToast(messageResId: Int)

    @Skip
    fun showAlertDialog(messageResId: Int)

    @AddToEndSingle
    fun showDrawerMenuScreen()

    @AddToEndSingle
    fun showProgress()

    @AddToEndSingle
    fun hideProgress()

}