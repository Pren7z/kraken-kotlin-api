package com.renzler.shared.apis.kraken.impl

sealed class KrakenAPIResponse<out T> {

    data class Success<out T>(val data: T) : KrakenAPIResponse<T>()

    data class Failure(val throwable: Throwable?) : KrakenAPIResponse<Nothing>()


}