package jt.projects.cardinform.repos.local

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.cardinform.model.CardEntity


class CardsRepoRoomImpl(private val db: AppDatabase) : ICardsLocalRepository {

    override fun getCards(): Single<List<CardEntity>> =
        Single.fromCallable { db.dao().getAllCards() }

    override fun saveCard(card: CardEntity) {
        Completable.fromCallable { db.dao().insert(card) }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}