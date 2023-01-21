package jt.projects.cardinform.viewmodel

import jt.projects.cardinform.model.CardEntity


sealed class CardData {
    data class Success(val serverResponseData: CardEntity) :
        CardData()

    data class Error(val error: Throwable) : CardData()
    data class Loading(val progress: Int?) : CardData()
}