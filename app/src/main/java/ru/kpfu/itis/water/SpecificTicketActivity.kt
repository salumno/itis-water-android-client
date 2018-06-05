package ru.kpfu.itis.water

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_specific_ticket.*
import kotlinx.android.synthetic.main.content_specific_ticket.*
import ru.kpfu.itis.water.adapters.TicketMessageAdapter
import ru.kpfu.itis.water.model.ItisWaterTicketItem

class SpecificTicketActivity : AppCompatActivity() {

    companion object {
        const val TICKET_ITEM_KEY = "ticketItem"
    }

    private val messagesList by lazy {
        ticketMessagesRecyclerView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_ticket)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val ticket: ItisWaterTicketItem = intent.getParcelableExtra(TICKET_ITEM_KEY)

        messagesList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }

        initAdapter()

        showTicketData(ticket)
    }

    private fun initAdapter() {
        if (messagesList.adapter == null) {
            messagesList.adapter = TicketMessageAdapter()
        }
    }

    private fun showTicketData(ticketItem: ItisWaterTicketItem) {
        specific_ticket_text.text = ticketItem.text
        specific_ticket_date.text = ticketItem.date
        specific_ticket_status.text = ticketItem.status
        (messagesList.adapter as TicketMessageAdapter).addMessages(ticketItem.messages)
    }

}
