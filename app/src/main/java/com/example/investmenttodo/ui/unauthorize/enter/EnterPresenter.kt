package com.example.investmenttodo.ui.unauthorize.enter

import com.example.investmenttodo.R
import com.example.investmenttodo.app.App
import com.example.investmenttodo.base.BasePresenter
import com.example.investmenttodo.dataclass.User
import com.example.investmenttodo.manager.DataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState

@InjectViewState
class EnterPresenter : BasePresenter<EnterView>() {

    private var nameText = ""
    private var passwordText = ""

    fun onNameTextChanged(text: String) {
        nameText = text.trim()
        checkTextValid()
    }

    fun onPasswordTextChanged(text: String) {
        passwordText = text.trim()
        checkTextValid()
    }

    private fun checkTextValid() {
        viewState.changeEnableEnterNowButton(nameText.length > 1 && passwordText.length > 5)
    }

    fun onEnterNowButtonClicked() {
        viewState.showProgress()
        viewState.hideKeyboard()
        presenterScope.launch {
            val userList = App.instance.database.userDao().getAll()
            var currentUser: User? = null
            userList.forEach {
                if (it.name == nameText) {
                    currentUser = it
                    return@forEach
                }
            }
            delay(1000)
            withContext(Dispatchers.Main) {
                viewState.hideProgress()
                currentUser?.let {
                    if (it.password != passwordText) {
                        viewState.showAlertDialog(R.string.error, R.string.enter_password_error)
                    } else {
                        App.instance.prefs.userName = nameText
                        viewState.showDrawerMenuScreen()
                    }
                } ?: viewState.showAlertDialog(R.string.error, R.string.enter_name_error)
            }
        }
    }
}