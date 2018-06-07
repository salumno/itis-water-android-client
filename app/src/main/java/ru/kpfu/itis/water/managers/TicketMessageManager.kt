package ru.kpfu.itis.water.managers

import ru.kpfu.itis.water.api.RestAPI
import ru.kpfu.itis.water.extensions.createIWTicketMessageItemsBy
import ru.kpfu.itis.water.model.ItisWaterTicketMessageItem
import rx.Observable

/**
 * Created by Melnikov Semyon on 06.06.18.
 * Higher School ITIS KFU
 */
class TicketMessageManager(private val api: RestAPI = RestAPI()) {
    fun addNewMessage(message: String, userId: Long, ticketId: Long): Observable<List<ItisWaterTicketMessageItem>> {
        return Observable.create {
            subscriber ->
            val callResponse = api.addTicketMessage(
                    message = message,
                    userId = userId,
                    ticketId = ticketId
            )
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val messagesResponse = response.body()
                subscriber.onNext(createIWTicketMessageItemsBy(messagesResponse))
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}