package com.example.investmenttodo.ui.authorize.drawerview

import com.example.investmenttodo.base.BaseView
import com.example.investmenttodo.dataclass.User
import moxy.viewstate.strategy.alias.AddToEndSingle

interface DrawerViewView : BaseView {

    @AddToEndSingle
    fun initView(user: User)

}