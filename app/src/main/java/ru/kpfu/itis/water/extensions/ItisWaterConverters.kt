package ru.kpfu.itis.water.extensions

import ru.kpfu.itis.water.api.ItisWaterTicketDataResponse
import ru.kpfu.itis.water.api.ItisWaterTicketMessageDataResponse
import ru.kpfu.itis.water.api.ItisWaterUserResponse
import ru.kpfu.itis.water.model.ItisWaterTicketItem
import ru.kpfu.itis.water.model.ItisWaterTicketMessageItem
import ru.kpfu.itis.water.model.ItisWaterUserItem

/**
 * Created by Melnikov Semyon on 06.06.18.
 * Higher School ITIS KFU
 */

fun createIWTicketItemBy(
        ticketResponse: ItisWaterTicketDataResponse
): ItisWaterTicketItem =
        ItisWaterTicketItem(
                id = ticketResponse.id,
                text = ticketResponse.text,
                status = ticketResponse.status,
                date = ticketResponse.date,
                messages = createIWTicketMessageItemsBy(ticketResponse.messages)
        )

fun createIWTicketMessageItemsBy(
        ticketMessagesResponse: List<ItisWaterTicketMessageDataResponse>
): List<ItisWaterTicketMessageItem> =
        ticketMessagesResponse.map {
            ItisWaterTicketMessageItem(
                    id = it.id,
                    text = it.text,
                    date = it.date,
                    ticketId = it.ticketId,
                    user = createIWUserItemBy(it.user)
            )
        }

fun createIWUserItemBy(
        userResponse: ItisWaterUserResponse
): ItisWaterUserItem =
        ItisWaterUserItem(
                id = userResponse.id,
                name = userResponse.name,
                surname = userResponse.surname,
                role = userResponse.role
        )