package jt.projects.cardinform.repos.remote

import io.reactivex.rxjava3.core.Single
import jt.projects.cardinform.model.CardEntity
import jt.projects.cardinform.model.toCardEntity

class CardRepoRetrofitImpl(private val api: CardAPI) : ICardRemoteRepository {

    override fun getCardByNumber(cardNumber: String): Single<CardEntity> =
        api.getCard(cardNumber).map { card ->
            if (card.bank != null) {
                card.toCardEntity(cardNumber)
            } else {
                throw RuntimeException("Данных не найдено (response body = null)")
            }

        }
}