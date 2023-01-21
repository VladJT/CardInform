package jt.projects.cardinform.repos

import io.reactivex.rxjava3.core.Single
import jt.projects.cardinform.model.Card

interface ICardRemoteRepository {
    fun getCardByNumber(cardNumber: String): Single<Card>
}