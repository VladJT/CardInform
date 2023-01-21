package jt.projects.cardinform.repos

import io.reactivex.rxjava3.core.Single
import jt.projects.cardinform.model.Card
import retrofit2.http.GET
import retrofit2.http.Path

interface CardAPI {
    @GET("{cardNumber}")
    fun getCard(@Path("cardNumber") cardNumber: String): Single<Card>
}