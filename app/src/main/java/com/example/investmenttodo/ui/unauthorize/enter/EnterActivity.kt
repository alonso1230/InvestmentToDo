package com.example.investmenttodo.ui.unauthorize.enter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import com.example.investmenttodo.R
import com.example.investmenttodo.base.BaseActivity
import com.example.investmenttodo.ui.authorize.drawermenu.DrawerMenuActivity
import kotlinx.android.synthetic.main.activity_enter.*
import moxy.presenter.InjectPresenter

class EnterActivity : BaseActivity(), EnterView {

    override val layoutRes = R.layout.activity_enter

    @InjectPresenter
    lateinit var presenter: EnterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.main_enter)
        initListeners()
    }

    private fun initListeners() {
        etEnterName.doOnTextChanged { text, start, before, count -> presenter.onNameTextChanged(text.toString()) }
        etEnterPassword.doOnTextChanged { text, start, before, count -> presenter.onPasswordTextChanged(text.toString()) }
        btnEnterNow.setOnClickListener { presenter.onEnterNowButtonClicked() }
    }

    override fun changeEnableEnterNowButton(enable: Boolean) {
        btnEnterNow.isEnabled = enable
    }

    override fun showAlertDialog(titleResId: Int, messageResId: Int) {
        AlertDialog.Builder(this).setTitle(titleResId).setMessage(messageResId).setPositiveButton(R.string.ok, null).show()
    }

    override fun showDrawerMenuScreen() {
        val intent = Intent(this, DrawerMenuActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun showProgress() {
        pbEnter.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbEnter.visibility = View.GONE
    }
}