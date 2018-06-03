package ru.kpfu.itis.water

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_ticket.*
import kotlinx.android.synthetic.main.content_ticket.*

class TicketActivity : AppCompatActivity() {

    companion object {
        const val NEWS_ID_KEY = "userId"
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

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        initAdapter()

        if (savedInstanceState == null) {
            //TODO
        }
    }

    fun requestTickets(userId: Long) {
        ticketManager.getUserTickets(userId).subscribe(
                { receivedTickets ->
                    (ticketList.adapter as TicketAdapter).addTickets(receivedTickets)
                },
                { e ->
                    Snackbar.make(ticketList, e.message ?: "" , Snackbar.LENGTH_LONG)
                }
        )
    }

    fun initAdapter() {
        if (ticketList.adapter == null) {
            ticketList.adapter = TicketAdapter()
        }
    }

}
