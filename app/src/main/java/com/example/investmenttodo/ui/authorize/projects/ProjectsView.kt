package com.example.investmenttodo.ui.authorize.projects

import com.example.investmenttodo.base.BaseView
import com.example.investmenttodo.dataclass.Project
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface ProjectsView : BaseView {

    @AddToEndSingle
    fun setProjectsData(projectsList: MutableList<Project>)

    @Skip
    fun showProjectDetailScreen(project: Project)

    @AddToEndSingle
    fun showEmptyView()

}