package ru.kpfu.itis.water

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_specific_ticket.*
import kotlinx.android.synthetic.main.content_specific_ticket.*
import ru.kpfu.itis.water.adapters.TicketMessageAdapter
import ru.kpfu.itis.water.managers.TicketMessageManager
import ru.kpfu.itis.water.model.ItisWaterTicketItem
import rx.schedulers.Schedulers

class SpecificTicketActivity : AppCompatActivity() {

    companion object {
        const val TICKET_ITEM_KEY = "ticketItem"
    }

    private val ticketMessageManager by lazy {
        TicketMessageManager()
    }

    private val messagesList by lazy {
        ticketMessagesRecyclerView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_ticket)
        setSupportActionBar(toolbar)

        val ticket: ItisWaterTicketItem = intent.getParcelableExtra(TICKET_ITEM_KEY)

        messagesList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }

        add_message_button.setOnClickListener {
            handleNewMessageButton()
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

    private fun handleNewMessageButton() {
        val message = new_message_edit_text.text.toString()
        ticketMessageManager.addNewMessage(message)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { receivedMessages ->
                            (messagesList.adapter as TicketMessageAdapter).addMessages(receivedMessages)
                            new_message_edit_text.setText("")
                        },
                        { e ->
                            Snackbar.make(messagesList, e.message ?: "" , Snackbar.LENGTH_LONG)
                        }
                )
    }

}
