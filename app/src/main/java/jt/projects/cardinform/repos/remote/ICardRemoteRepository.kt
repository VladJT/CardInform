package jt.projects.cardinform.repos.remote

import io.reactivex.rxjava3.core.Single
import jt.projects.cardinform.model.CardEntity

interface ICardRemoteRepository {
    fun getCardByNumber(cardNumber: String): Single<CardEntity>
}