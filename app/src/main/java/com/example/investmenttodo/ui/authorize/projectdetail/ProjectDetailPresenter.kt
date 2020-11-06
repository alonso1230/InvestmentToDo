package com.example.investmenttodo.ui.authorize.projectdetail

import com.example.investmenttodo.base.BasePresenter
import com.example.investmenttodo.dataclass.Project
import com.example.investmenttodo.manager.DataManager
import moxy.InjectViewState

@InjectViewState
class ProjectDetailPresenter : BasePresenter<ProjectDetailView>() {

    private lateinit var project: Project

    private var currentTab = ProjectDetailActivity.TAB_OPEN
    private val openList = DataManager.getCurrentProject()!!.openCards
    private val inProgressList = DataManager.getCurrentProject()!!.inProgressCards
    private val resolvedList = DataManager.getCurrentProject()!!.resolveCards

    override fun onFirstViewAttach() {
        project = DataManager.getCurrentProject()!!
        viewState.initView(project)
    }

    override fun attachView(view: ProjectDetailView?) {
        super.attachView(view)
        when (currentTab) {
            ProjectDetailActivity.TAB_OPEN -> viewState.setData(openList)
            ProjectDetailActivity.TAB_IN_PROGRESS -> viewState.setData(inProgressList)
            ProjectDetailActivity.TAB_RESOLVED -> viewState.setData(resolvedList)
        }
    }

    fun onTabChanged(currentTab: Int) {
        this.currentTab = currentTab
        when (currentTab) {
            ProjectDetailActivity.TAB_OPEN -> viewState.setData(openList)
            ProjectDetailActivity.TAB_IN_PROGRESS -> viewState.setData(inProgressList)
            ProjectDetailActivity.TAB_RESOLVED -> viewState.setData(resolvedList)
        }
    }

    fun onItemClicked(position: Int) {
        val card = when (currentTab) {
            ProjectDetailActivity.TAB_OPEN -> openList[position]
            ProjectDetailActivity.TAB_IN_PROGRESS -> inProgressList[position]
            ProjectDetailActivity.TAB_RESOLVED -> resolvedList[position]
            else -> openList[position]
        }
        DataManager.setCurrentCard(card)
        viewState.showCardDetailScreen(card)
    }

    fun onActionEditClicked() {
        viewState.showEditTextDialog()
    }

    fun onRenameClicked(projectName: String) {
        DataManager.getCurrentProject()?.name = projectName
        viewState.setTitleScreen(projectName)
    }
}