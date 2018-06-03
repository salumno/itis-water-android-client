package ru.kpfu.itis.water

import ru.kpfu.itis.water.api.RestAPI
import rx.Observable

/**
 * Created by Melnikov Semyon on 03.06.18.
 * Higher School ITIS KFU
 */
class TicketManager(private val api: RestAPI = RestAPI()) {
    fun getUserTickets(userId: Long): Observable<List<ItisWaterTicketItem>> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getUserTickets(userId)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val tickets = response.body().data.map {
                    val ticketResponse = it
                    ItisWaterTicketItem(
                            id = ticketResponse.id,
                            text = ticketResponse.text,
                            status = ticketResponse.status,
                            date = ticketResponse.date
                    )
                }
                subscriber.onNext(tickets)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}