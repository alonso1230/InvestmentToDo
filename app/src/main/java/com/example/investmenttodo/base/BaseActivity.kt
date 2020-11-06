package com.example.investmenttodo.base

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import moxy.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity(), BaseView {

    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    protected fun changeVisibleToolbar(visible: Boolean) {
        supportActionBar?.let { if (visible) it.show() else it.hide() }
    }

    protected fun changeVisibleStatusBar(visible: Boolean) {
        if (visible)
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        else
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun showKeyboard() {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }

    override fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun hideKeyboard(editText: EditText? = null) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText?.windowToken, 0)
    }
}