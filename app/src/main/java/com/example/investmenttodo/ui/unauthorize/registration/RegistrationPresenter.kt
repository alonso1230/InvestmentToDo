package com.example.investmenttodo.ui.unauthorize.registration

import com.example.investmenttodo.R
import com.example.investmenttodo.app.App
import com.example.investmenttodo.base.BasePresenter
import com.example.investmenttodo.dataclass.User
import com.example.investmenttodo.util.RandomColors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState

@InjectViewState
class RegistrationPresenter : BasePresenter<RegistrationView>() {

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
        viewState.changeEnableRegistrationNowButton(nameText.length > 1 && passwordText.length > 5)
    }

    fun onRegistrationNowClicked() {
        viewState.showProgress()
        viewState.hideKeyboard()
        presenterScope.launch {
            val userList = App.instance.database.userDao().getAll()
            var hasUser = false
            userList.forEach {
                if (it.name == nameText) {
                    hasUser = true
                    return@forEach
                }
            }
            if (!hasUser) {
                val newUser = User(nameText, passwordText, RandomColors().color)
                App.instance.database.userDao().insert(newUser)
                App.instance.prefs.userName = nameText
            }
            delay(1000)
            withContext(Dispatchers.Main) {
                viewState.hideProgress()
                if (hasUser) {
                    viewState.showAlertDialog(R.string.registration_has_user)
                } else {
                    viewState.showLongToast(R.string.registration_success)
                    viewState.showDrawerMenuScreen()
                }
            }
        }
    }
}