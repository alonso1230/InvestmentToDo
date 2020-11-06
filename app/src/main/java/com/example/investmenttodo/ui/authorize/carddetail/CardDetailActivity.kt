package com.example.investmenttodo.ui.authorize.carddetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.investmenttodo.R
import com.example.investmenttodo.app.Constants
import com.example.investmenttodo.base.BaseActivity
import com.example.investmenttodo.dataclass.Card
import com.example.investmenttodo.dataclass.Comment
import com.example.investmenttodo.dataclass.User
import com.example.investmenttodo.util.extension.toDate
import kotlinx.android.synthetic.main.activity_card_detail.*
import moxy.presenter.InjectPresenter

class CardDetailActivity : BaseActivity(), CardDetailView {

    override val layoutRes = R.layout.activity_card_detail

    @InjectPresenter
    lateinit var presenter: CardDetailPresenter

    private val cardDetailAdapter: CardDetailAdapter by lazy {
        CardDetailAdapter().apply {
            rvComment.layoutManager = LinearLayoutManager(this@CardDetailActivity, LinearLayoutManager.VERTICAL, false)
            rvComment.adapter = this
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.card_detail_title)
        initListeners()
    }

    private fun initListeners() {
        btnCardDetailOpen.setOnClickListener { presenter.onOpenClicked() }
        btnCardDetailInProgress.setOnClickListener { presenter.onInProgressClicked() }
        btnCardDetailResolve.setOnClickListener { presenter.onResolveClicked() }
        ivAddComment.setOnClickListener { presenter.onAddCommentClicked(etComment.text.toString()) }
    }

    override fun initView(card: Card, createUser: User, executorUser: User, status: String) {
        tvCardName.text = card.name
        card.description?.let { tvCardDescription.text = it }
                ?: run { tvCardDescription.visibility = View.GONE }
        setStatusCardText(status)
        cvCardCreateUser.setFirstLetter(createUser.name)
        cvCardCreateUser.setCircleColor(createUser.backgroundColor)
        cvCardCreateUser.textSize = 16f
        tvCardCreateUser.text = createUser.name
        cvCardExecutorUser.setFirstLetter(executorUser.name)
        cvCardExecutorUser.setCircleColor(executorUser.backgroundColor)
        tvCardExecutorUser.text = executorUser.name
        tvCardMembers.text = card.members.joinToString()
        tvCardCreateDate.text = card.createDate.toDate(Constants.DATE_FORMAT)
        card.resolveDate?.let { tvCardResolveDate.text = it.toDate(Constants.DATE_FORMAT) }
                ?: run { llCardResolveDate.visibility = View.GONE }
        card.deadlineDate?.let { tvCardDeadlineDate.text = it.toDate(Constants.DATE_FORMAT) }
                ?: run { llCardDeadlineDate.visibility = View.GONE }
        card.comments?.let { cardDetailAdapter.setData(it) }
    }

    override fun setStatusCardText(status: String) {
        when (status) {
            Constants.OPEN -> {
                btnCardDetailOpen.visibility = View.GONE
                btnCardDetailInProgress.visibility = View.VISIBLE
                btnCardDetailResolve.visibility = View.VISIBLE
                tvCardStatus.text = getString(R.string.project_detail_tab_open)
            }
            Constants.IN_PROGRESS -> {
                btnCardDetailOpen.visibility = View.VISIBLE
                btnCardDetailOpen.text = getString(R.string.card_detail_stop_progress)
                btnCardDetailInProgress.visibility = View.GONE
                btnCardDetailResolve.visibility = View.VISIBLE
                tvCardStatus.text = getString(R.string.project_detail_tab_in_progress)
            }
            Constants.RESOLVE -> {
                btnCardDetailOpen.visibility = View.VISIBLE
                btnCardDetailInProgress.visibility = View.VISIBLE
                btnCardDetailResolve.visibility = View.GONE
                tvCardStatus.text = getString(R.string.project_detail_tab_resolved)
            }
        }
    }

    override fun addComment(comment: Comment) {
        cardDetailAdapter.notifyDataSetChanged()
        etComment.text = null
        Toast.makeText(this, getString(R.string.added), Toast.LENGTH_SHORT).show()
        hideKeyboard(etComment)
    }
}