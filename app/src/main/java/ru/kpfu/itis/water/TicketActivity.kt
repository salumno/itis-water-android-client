package ru.kpfu.itis.water

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_ticket.*
import kotlinx.android.synthetic.main.content_ticket.*
import ru.kpfu.itis.water.adapters.TicketAdapter
import ru.kpfu.itis.water.managers.TicketManager
import ru.kpfu.itis.water.model.ItisWaterTicketItem
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class TicketActivity : AppCompatActivity(), TicketAdapter.onTicketSelectedListener {
    companion object {
        const val USER_ID_KEY = "userId"
    }

    private val ticketManager by lazy {
        TicketManager()
    }

    private val ticketList by lazy {
        ticketRecyclerView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)
        setSupportActionBar(toolbar)

        val userId: Long = savedInstanceState?.getLong(USER_ID_KEY) ?: getUserIdFromIntent()

        ticketList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        initAdapter()
        requestTickets(userId)
    }

    override fun onTicketSelected(ticketItem: ItisWaterTicketItem) {
        val intent = Intent(this, SpecificTicketActivity::class.java)
        intent.putExtra(SpecificTicketActivity.TICKET_ITEM_KEY, ticketItem)
        startActivity(intent)
    }

    private fun requestTickets(userId: Long) {
        ticketManager.getUserTickets(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { receivedTickets ->
                        (ticketList.adapter as TicketAdapter).addTickets(receivedTickets)
                    },
                    { e ->
                        Snackbar.make(ticketList, e.message ?: "" , Snackbar.LENGTH_LONG)
                    }
                )
    }

    private fun initAdapter() {
        if (ticketList.adapter == null) {
            ticketList.adapter = TicketAdapter(this)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(USER_ID_KEY, getUserIdFromIntent())
    }

    private fun getUserIdFromIntent() = intent.getLongExtra(USER_ID_KEY, -1)
}
