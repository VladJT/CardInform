package jt.projects.cardinform.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class CardEntity(
    @PrimaryKey
    val cardNumber: String,
    val scheme: String?,
    val brand: String?,
    val len: Int?,
    val luhn: String?,
    val type: String?,
    val prepaid: String?,
    val countryName: String?,
    val latitude: String?,
    val longitude: String?,
    val bankName: String?,
    val phone: String?,
    val url: String?,
    val dateOfResponse: String = "${SimpleDateFormat("dd.MM.yy hh:mm:ss").format(Date())}"
)