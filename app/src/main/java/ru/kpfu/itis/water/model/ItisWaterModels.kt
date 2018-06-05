package ru.kpfu.itis.water.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Melnikov Semyon on 06.06.18.
 * Higher School ITIS KFU
 */

data class ItisWaterTicketItem(
        val id: Long,
        val text: String,
        val status: String,
        val date: String,
        val messages: List<ItisWaterTicketMessageItem>
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(ItisWaterTicketMessageItem))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(text)
        parcel.writeString(status)
        parcel.writeString(date)
        parcel.writeTypedList(messages)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ItisWaterTicketItem> {
        override fun createFromParcel(parcel: Parcel): ItisWaterTicketItem = ItisWaterTicketItem(parcel)

        override fun newArray(size: Int): Array<ItisWaterTicketItem?> = arrayOfNulls(size)
    }
}

data class ItisWaterTicketMessageItem(
        val id: Long,
        val text: String,
        val date: String,
        val ticketId: Long,
        val user: ItisWaterUserItem
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readParcelable(ItisWaterUserItem::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(text)
        parcel.writeString(date)
        parcel.writeLong(ticketId)
        parcel.writeParcelable(user, flags)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ItisWaterTicketMessageItem> {
        override fun createFromParcel(parcel: Parcel): ItisWaterTicketMessageItem = ItisWaterTicketMessageItem(parcel)

        override fun newArray(size: Int): Array<ItisWaterTicketMessageItem?> = arrayOfNulls(size)
    }
}

data class ItisWaterUserItem(
        val id: Long,
        val name: String,
        val surname: String,
        val role: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(role)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ItisWaterUserItem> {
        override fun createFromParcel(parcel: Parcel): ItisWaterUserItem = ItisWaterUserItem(parcel)

        override fun newArray(size: Int): Array<ItisWaterUserItem?> = arrayOfNulls(size)
    }
}