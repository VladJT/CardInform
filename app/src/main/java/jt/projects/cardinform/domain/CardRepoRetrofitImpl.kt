package jt.projects.cardinform.domain

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.cardinform.BuildConfig
import jt.projects.cardinform.model.Card
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CardRepoRetrofitImpl() : ICardRemoteRepository {

    fun getApi(): CardAPI = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build().create(CardAPI::class.java)

    override fun getCardByNumber(cardNumber: String): Single<Card> =
        getApi().getCard(cardNumber)
            .onErrorReturn { throw RuntimeException("Ошибка получения данных по http") }
            .subscribeOn(Schedulers.io())

}