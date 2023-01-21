package jt.projects.cardinform.viewmodel

import jt.projects.cardinform.model.Card


sealed class CardData {
    data class Success(val serverResponseData: Card) :
        CardData()

    data class Error(val error: Throwable) : CardData()
    data class Loading(val progress: Int?) : CardData()
}