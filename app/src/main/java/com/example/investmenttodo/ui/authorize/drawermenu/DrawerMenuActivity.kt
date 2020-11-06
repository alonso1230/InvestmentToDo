package com.example.investmenttodo.ui.authorize.drawermenu

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.example.investmenttodo.R
import com.example.investmenttodo.app.App
import com.example.investmenttodo.base.BaseActivity
import com.example.investmenttodo.base.BaseFragment
import com.example.investmenttodo.ui.authorize.projects.ProjectsFragment
import com.example.investmenttodo.ui.authorize.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_drawer_menu.*
import kotlinx.android.synthetic.main.include_app_bar.*
import moxy.presenter.InjectPresenter

class DrawerMenuActivity : BaseActivity(), DrawerMenuView {

    override val layoutRes = R.layout.activity_drawer_menu

    @InjectPresenter
    lateinit var presenter: DrawerMenuPresenter

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbarDrawerMenu)

        toggle = ActionBarDrawerToggle(this, dlDrawerMenu, toolbarDrawerMenu, R.string.drawer_open, R.string.drawer_close)
        dlDrawerMenu.addDrawerListener(toggle)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    fun openProjectFragment() {
        presenter.openFragment(ProjectsFragment(), getString(R.string.drawer_menu_projects))
    }

    fun openSettingsFragment() {
        presenter.openFragment(SettingsFragment(), getString(R.string.drawer_menu_settings))
    }

    override fun openFragment(fragment: BaseFragment, tag: String) = with(supportFragmentManager.beginTransaction()) {
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        replace(R.id.drawerContainer, fragment, tag).commitAllowingStateLoss()
        tvTitleDrawerMenu.text = tag
        closeDrawer()
    }

    private fun closeDrawer() {
        if (dlDrawerMenu.isDrawerOpen(GravityCompat.START)) {
            dlDrawerMenu.closeDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() {
        if (dlDrawerMenu.isDrawerOpen(GravityCompat.START)) {
            dlDrawerMenu.closeDrawer(GravityCompat.START)
        } else if (!tvTitleDrawerMenu.text.toString().equals(getString(R.string.drawer_menu_projects), true)) {
            presenter.openFragment(ProjectsFragment(), App.instance.getString(R.string.drawer_menu_projects))
        } else {
            showQuitDialog()
        }
    }

    private fun showQuitDialog() {
        AlertDialog.Builder(this)
                .setMessage(R.string.drawer_menu_logout_dialog_message)
                .setPositiveButton(R.string.yes) { _, _ -> finish() }
                .setNegativeButton(R.string.no, null)
                .show()
    }
}