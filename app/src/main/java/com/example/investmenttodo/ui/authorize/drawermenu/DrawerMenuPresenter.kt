package com.example.investmenttodo.ui.authorize.drawermenu

import com.example.investmenttodo.R
import com.example.investmenttodo.app.App
import com.example.investmenttodo.base.BaseFragment
import com.example.investmenttodo.base.BasePresenter
import com.example.investmenttodo.ui.authorize.projects.ProjectsFragment
import moxy.InjectViewState

@InjectViewState
class DrawerMenuPresenter : BasePresenter<DrawerMenuView>() {

    override fun onFirstViewAttach() {
        openFragment(ProjectsFragment(), App.instance.getString(R.string.drawer_menu_projects))
    }

    fun openFragment(fragment: BaseFragment, tag: String) {
        viewState.openFragment(fragment, tag)
    }
}