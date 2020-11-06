package com.example.investmenttodo.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import moxy.MvpPresenter

abstract class BasePresenter<T : BaseView> : MvpPresenter<T>() {

    protected val presenterScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onDestroy() {
        presenterScope.cancel()
        super.onDestroy()
    }
}