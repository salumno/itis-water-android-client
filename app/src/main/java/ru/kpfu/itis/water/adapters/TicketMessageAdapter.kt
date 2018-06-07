package ru.kpfu.itis.water.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.ticket_message_item.view.*
import ru.kpfu.itis.water.R
import ru.kpfu.itis.water.extensions.inflate
import ru.kpfu.itis.water.model.ItisWaterTicketMessageItem
import java.util.ArrayList

/**
 * Created by Melnikov Semyon on 05.06.18.
 * Higher School ITIS KFU
 */
class TicketMessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var messages: ArrayList<ItisWaterTicketMessageItem> = ArrayList()

    inner class TicketMessageViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.ticket_message_item)) {
        private val userName = itemView.message_name
        private val userSurname = itemView.message_surname
        private val text = itemView.message_text
        private val date = itemView.message_date

        fun bind(messageItem: ItisWaterTicketMessageItem) {
            userName.text = messageItem.author.name
            userSurname.text = messageItem.author.surname
            text.text = messageItem.text
            date.text = messageItem.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            TicketMessageViewHolder(parent)

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) =
            (holder as TicketMessageViewHolder).bind(messages[position])

    fun addMessages(ticketMessages: List<ItisWaterTicketMessageItem>) {
        messages.clear()
        messages.addAll(ticketMessages)
    }
}