package ru.kpfu.itis.water.managers

import ru.kpfu.itis.water.api.RestAPI
import ru.kpfu.itis.water.extensions.createIWTicketItemBy
import ru.kpfu.itis.water.model.ItisWaterTicketItem
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
                    createIWTicketItemBy(it)
                }
                subscriber.onNext(tickets)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}