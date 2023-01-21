package jt.projects.cardinform.model

data class Card(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: Number,
    val prepaid: String,
    val scheme: String,
    val type: String,
    val countryName: String,
)

fun Card.toCardEntity(num: String): CardEntity = CardEntity(
    cardNumber = num,
    scheme = scheme,
    brand = brand,
    len = number.length,
    luhn = number.luhn,
    type = type,
    latitude = country?.latitude,
    longitude = country?.longitude,
    prepaid = prepaid,
    countryName = country?.name,
    bankName = bank?.name,
    phone = bank?.phone,
    url = bank?.url
)