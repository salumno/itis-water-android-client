package ru.kpfu.itis.water

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.ticket_item.view.*
import ru.kpfu.itis.water.api.inflate

/**
 * Created by Melnikov Semyon on 03.06.18.
 * Higher School ITIS KFU
 */
class TicketAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items : ArrayList<ItisWaterTicketItem> = ArrayList()

    inner class TicketViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.ticket_item)) {
        private val text = itemView.ticket_text
        private val status = itemView.ticket_status
        private val date = itemView.ticket_date

        fun bind(item: ItisWaterTicketItem) {
            text.text = item.text
            status.text = item.status
            date.text = item.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            TicketViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as TicketViewHolder).bind(items[position])
    }

    fun addTickets(tickets: List<ItisWaterTicketItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}