package com.example.investmenttodo.ui.main

import android.content.Intent
import android.os.Bundle
import com.example.investmenttodo.R
import com.example.investmenttodo.base.BaseActivity
import com.example.investmenttodo.ui.authorize.drawermenu.DrawerMenuActivity
import com.example.investmenttodo.ui.unauthorize.enter.EnterActivity
import com.example.investmenttodo.ui.unauthorize.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_main.*
import moxy.presenter.InjectPresenter

class MainActivity : BaseActivity(), MainView {

    override val layoutRes = R.layout.activity_main

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeVisibleToolbar(false)
        changeVisibleStatusBar(false)
        initListeners()
    }

    private fun initListeners() {
        btnRegistration.setOnClickListener { startActivity(Intent(this, RegistrationActivity::class.java)) }
        btnEnter.setOnClickListener { startActivity(Intent(this, EnterActivity::class.java)) }
    }

    override fun showDrawerMenuScreen() {
        val intent = Intent(this, DrawerMenuActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}