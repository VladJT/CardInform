package jt.projects.cardinform.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.cardinform.model.CardEntity
import jt.projects.cardinform.repos.local.ICardsLocalRepository
import jt.projects.cardinform.repos.remote.ICardRemoteRepository
import jt.projects.cardinform.utils.notifyObserver
import retrofit2.HttpException
import javax.inject.Inject

@SuppressLint("CheckResult")
class CardViewModel : ViewModel() {
    private val currentCard: MutableLiveData<CardData> =
        MutableLiveData()

    private val cards: MutableLiveData<MutableList<CardEntity>> =
        MutableLiveData()

    @Inject
    lateinit var cardRemoteRepo: ICardRemoteRepository

    @Inject
    lateinit var cardLocalRepo: ICardsLocalRepository

    fun getCardLiveData(): LiveData<CardData> = currentCard

    fun getCardsHistoryLiveData(): MutableLiveData<MutableList<CardEntity>> = cards

    fun loadCardsFromLocalRepo() {
        cards.value = mutableListOf<CardEntity>()
        cardLocalRepo.getCards()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                cards.value?.addAll(data)
                cards.notifyObserver()
            }, { e ->
                Log.d("TAG", e.message.toString())
            })
    }

    @SuppressLint("CheckResult")
    fun loadCardByNumber(number: String) {
        currentCard.value = CardData.Loading(null)
        if (number.isBlank()) {
            currentCard.value = CardData.Error(RuntimeException("Введите число"))
        } else {
            cardRemoteRepo.getCardByNumber(number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    currentCard.value = CardData.Success(data)
                    cardLocalRepo.saveCard(data)
                }, { e ->
                    onError(e)
                })
        }
    }

    private fun onError(e: Throwable) {
        val httpError = e as? HttpException
        currentCard.value =
            when (httpError?.code()) {
                400 -> CardData.Error(RuntimeException("Данных не найдено [400]"))
                404 -> CardData.Error(RuntimeException("Данных не найдено [404]"))
                // default
                else -> CardData.Error(e)
            }
    }

}