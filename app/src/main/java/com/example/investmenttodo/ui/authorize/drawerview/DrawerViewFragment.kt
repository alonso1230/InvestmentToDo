package com.example.investmenttodo.ui.authorize.drawerview

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.investmenttodo.R
import com.example.investmenttodo.app.App
import com.example.investmenttodo.base.BaseFragment
import com.example.investmenttodo.dataclass.User
import com.example.investmenttodo.ui.authorize.drawermenu.DrawerMenuActivity
import com.example.investmenttodo.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_drawer_menu.*
import moxy.presenter.InjectPresenter

class DrawerViewFragment : BaseFragment(), DrawerViewView {

    override val layoutRes: Int = R.layout.fragment_drawer_menu

    @InjectPresenter
    lateinit var presenter: DrawerViewPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListeners()
    }

    private fun initListeners() {
        tvDrawerMenuProjects.setOnClickListener { (activity as DrawerMenuActivity).openProjectFragment() }
        tvDrawerMenuSettings.setOnClickListener { (activity as DrawerMenuActivity).openSettingsFragment() }
        tvDrawerMenuLogout.setOnClickListener {
            App.instance.prefs.userName = ""
            activity?.finish()
            startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun initView(user: User) {
        cvHeaderCircleName.setFirstLetter(user.name)
        cvHeaderCircleName.setCircleColor(user.backgroundColor)
        tvHeaderName.text = user.name
    }
}