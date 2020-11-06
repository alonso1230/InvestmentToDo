package com.example.investmenttodo.ui.authorize.projects

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.investmenttodo.R
import com.example.investmenttodo.base.BaseFragment
import com.example.investmenttodo.dataclass.Project
import com.example.investmenttodo.ui.authorize.projectdetail.ProjectDetailActivity
import kotlinx.android.synthetic.main.fragment_projects.*
import moxy.presenter.InjectPresenter

class ProjectsFragment : BaseFragment(), ProjectsView {

    override val layoutRes = R.layout.fragment_projects

    @InjectPresenter
    lateinit var presenter: ProjectsPresenter

    private val projectsAdapter: ProjectsAdapter by lazy {
        ProjectsAdapter().apply {
            onItemClickListener = { presenter.onItemClicked(it) }
            rvProjects.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvProjects.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
            rvProjects.adapter = this
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapters()
    }

    private fun initAdapters() {
        projectsAdapter.onItemClickListener = { presenter.onItemClicked(it) }
        rvProjects.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvProjects.adapter = projectsAdapter
    }

    override fun setProjectsData(projectsList: MutableList<Project>) {
        projectsAdapter.setData(projectsList)
        llProjectsEmptyListContainer.visibility = View.GONE
        rvProjects.visibility = View.VISIBLE
    }

    override fun showProjectDetailScreen(project: Project) {
        startActivity(Intent(context, ProjectDetailActivity::class.java))
    }

    override fun showEmptyView() {
        llProjectsEmptyListContainer.visibility = View.VISIBLE
        rvProjects.visibility = View.GONE
    }
}