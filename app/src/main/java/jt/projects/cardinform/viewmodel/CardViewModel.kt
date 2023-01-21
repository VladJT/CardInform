package jt.projects.cardinform.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import jt.projects.cardinform.domain.CardRepoRetrofitImpl

class CardViewModel() : ViewModel() {
    private val currentCard: MutableLiveData<CardData> =
        MutableLiveData()

    private val cardRepo = CardRepoRetrofitImpl()

    fun getLiveData(): LiveData<CardData> {
        return currentCard
    }

    @SuppressLint("CheckResult")
    fun loadCardByNumber(number: String) {
        currentCard.value = CardData.Loading(null)
        cardRepo.getCardByNumber(number)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                currentCard.value = CardData.Success(data)
            }, { e ->
                currentCard.value = CardData.Error(e)
            })
    }

}