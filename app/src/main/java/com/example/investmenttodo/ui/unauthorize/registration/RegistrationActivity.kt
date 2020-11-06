package com.example.investmenttodo.ui.unauthorize.registration

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.investmenttodo.R
import com.example.investmenttodo.base.BaseActivity
import com.example.investmenttodo.ui.authorize.drawermenu.DrawerMenuActivity
import kotlinx.android.synthetic.main.activity_registration.*
import moxy.presenter.InjectPresenter

class RegistrationActivity : BaseActivity(), RegistrationView {

    override val layoutRes = R.layout.activity_registration

    @InjectPresenter
    lateinit var presenter: RegistrationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.main_registration)
        initListener()
    }

    private fun initListener() {
        etRegistrationName.doOnTextChanged { text, start, before, count -> presenter.onNameTextChanged(text.toString()) }
        etRegistrationPassword.doOnTextChanged { text, start, before, count -> presenter.onPasswordTextChanged(text.toString()) }
        btnRegistrationNow.setOnClickListener { presenter.onRegistrationNowClicked() }
    }

    override fun changeEnableRegistrationNowButton(enable: Boolean) {
        btnRegistrationNow.isEnabled = enable
    }

    override fun showLongToast(messageResId: Int) {
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
    }

    override fun showAlertDialog(messageResId: Int) {
        AlertDialog.Builder(this).setMessage(messageResId).setPositiveButton(R.string.ok, null).show()
    }

    override fun showDrawerMenuScreen() {
        val intent = Intent(this, DrawerMenuActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun showProgress() {
        pbRegistration.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbRegistration.visibility = View.GONE
    }
}