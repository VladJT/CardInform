package jt.projects.cardinform.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.cardinform.repos.ICardRemoteRepository
import retrofit2.HttpException
import javax.inject.Inject

class CardViewModel : ViewModel() {
    private val currentCard: MutableLiveData<CardData> =
        MutableLiveData()

    @Inject
    lateinit var cardRepo: ICardRemoteRepository

    fun getLiveData(): LiveData<CardData> = currentCard

    @SuppressLint("CheckResult")
    fun loadCardByNumber(number: String) {
        currentCard.value = CardData.Loading(null)
        if (number.isBlank()) {
            currentCard.value = CardData.Error(RuntimeException("Введите число"))
        } else {
            cardRepo.getCardByNumber(number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    currentCard.value = CardData.Success(data)
                }, { e ->
                    onError(e)
                })
        }
    }

    private fun onError(e: Throwable) {
        val httpError = e as? HttpException
        currentCard.value =
            when (httpError?.code()) {
                400 -> CardData.Error(RuntimeException("Данных не найдено"))
                404 -> CardData.Error(RuntimeException("Данных не найдено"))
                // default
                else -> CardData.Error(e)
            }
    }

}