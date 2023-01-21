package jt.projects.cardinform.model

data class Card(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: Number,
    val prepaid: String,
    val scheme: String,
    val type: String
)