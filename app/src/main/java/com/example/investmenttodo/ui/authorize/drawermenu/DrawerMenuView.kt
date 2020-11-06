package com.example.investmenttodo.ui.authorize.drawermenu

import com.example.investmenttodo.base.BaseFragment
import com.example.investmenttodo.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface DrawerMenuView : BaseView {

    @AddToEndSingle
    fun openFragment(fragment: BaseFragment, tag: String)

}