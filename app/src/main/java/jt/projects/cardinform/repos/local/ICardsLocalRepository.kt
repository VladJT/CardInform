package jt.projects.cardinform.repos.local

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import jt.projects.cardinform.model.CardEntity

interface ICardsLocalRepository {
    fun getCards(): Single<List<CardEntity>>
    fun saveCard(card: CardEntity)
}