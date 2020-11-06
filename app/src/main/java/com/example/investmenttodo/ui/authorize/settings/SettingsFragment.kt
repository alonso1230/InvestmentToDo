package com.example.investmenttodo.ui.authorize.settings

import com.example.investmenttodo.R
import com.example.investmenttodo.base.BaseFragment
import moxy.presenter.InjectPresenter

class SettingsFragment : BaseFragment(), SettingsView {

    override val layoutRes = R.layout.fragment_settings

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

}