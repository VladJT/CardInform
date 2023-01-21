package jt.projects.cardinform.repos

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.cardinform.model.Card

class CardRepoRetrofitImpl(private val api: CardAPI) : ICardRemoteRepository {

    override fun getCardByNumber(cardNumber: String): Single<Card> =
        api.getCard(cardNumber).subscribeOn(Schedulers.io())

}