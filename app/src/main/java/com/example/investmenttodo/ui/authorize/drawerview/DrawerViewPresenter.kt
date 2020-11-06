package com.example.investmenttodo.ui.authorize.drawerview

import com.example.investmenttodo.app.App
import com.example.investmenttodo.base.BasePresenter
import com.example.investmenttodo.manager.DataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState

@InjectViewState
class DrawerViewPresenter : BasePresenter<DrawerViewView>() {

    override fun onFirstViewAttach() {
        presenterScope.launch {
            DataManager.userList = App.instance.database.userDao().getAll()
            val currentUser = DataManager.getUserByName(App.instance.prefs.userName)
            withContext(Dispatchers.Main) {
                viewState.initView(currentUser)
            }
        }
    }
}