package com.example.investmenttodo.ui.authorize.projectdetail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.investmenttodo.R
import com.example.investmenttodo.base.BaseActivity
import com.example.investmenttodo.dataclass.Card
import com.example.investmenttodo.dataclass.Project
import com.example.investmenttodo.ui.authorize.carddetail.CardDetailActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_project_detail.*
import moxy.presenter.InjectPresenter


class ProjectDetailActivity : BaseActivity(), ProjectDetailView {

    override val layoutRes = R.layout.activity_project_detail

    @InjectPresenter
    lateinit var presenter: ProjectDetailPresenter
    private val cardDetailAdapter: ProjectDetailAdapter by lazy {
        ProjectDetailAdapter().apply {
            onItemClickListener = { presenter.onItemClicked(it) }
            rvProjectDetail.layoutManager = LinearLayoutManager(this@ProjectDetailActivity, LinearLayoutManager.VERTICAL, false)
            rvProjectDetail.adapter = this
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        tlProjectDetail.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                presenter.onTabChanged(tab.tag as Int)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun initView(project: Project) {
        title = project.name
        initTabLayout()
    }

    private fun initTabLayout() {
        tlProjectDetail.addTab(tlProjectDetail.newTab().setText(getString(R.string.project_detail_tab_open)).setTag(TAB_OPEN))
        tlProjectDetail.addTab(tlProjectDetail.newTab().setText(getString(R.string.project_detail_tab_in_progress)).setTag(TAB_IN_PROGRESS))
        tlProjectDetail.addTab(tlProjectDetail.newTab().setText(getString(R.string.project_detail_tab_resolved)).setTag(TAB_RESOLVED))
    }

    override fun setData(cardList: MutableList<Card>) {
        cardDetailAdapter.setData(cardList)
    }

    override fun showCardDetailScreen(card: Card) {
        startActivity(Intent(this, CardDetailActivity::class.java))
    }

    override fun showEditTextDialog() {
        val editText = EditText(this)
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.project_detail_rename_title))
            setView(editText)
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                presenter.onRenameClicked(editText.text.toString())
            }
            setNegativeButton(getString(R.string.no), null)
        }.show()
    }

    override fun setTitleScreen(title: String) {
        this.title = title
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_project_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionEdit -> {
                presenter.onActionEditClicked()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val TAB_OPEN = 0
        const val TAB_IN_PROGRESS = 1
        const val TAB_RESOLVED = 2
    }
}