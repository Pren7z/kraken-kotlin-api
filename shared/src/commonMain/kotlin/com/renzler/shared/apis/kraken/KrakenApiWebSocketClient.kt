package com.renzler.shared.apis.kraken


import com.renzler.shared.apis.kraken.domainWS.*
import com.renzler.shared.apis.kraken.impl.KrakenAPIResponse
import com.renzler.shared.apis.kraken.impl.KrakenAPIWebSocketResponse
import kotlinx.serialization.ExperimentalSerializationApi

interface KrakenApiWebSocketClient {


    /*
        get Ticker data
    */

    @ExperimentalSerializationApi
    suspend fun ticker(pair : List<String> , webSocketCallback: (result: KrakenAPIWebSocketResponse<Ticker>) -> Unit)


    /*
       get OHLC data
   */
    @ExperimentalSerializationApi
    suspend fun ohlc(pair : List<String> ,interval: Int?, webSocketCallback: (result: KrakenAPIWebSocketResponse<OHLC>) -> Unit)


    /*
     get Trades data
    */
    @ExperimentalSerializationApi
    suspend fun tradeFeed(pair : List<String>, webSocketCallback: (result: KrakenAPIWebSocketResponse<TradeFeed>) -> Unit)


    /*
     get Spread data
    */
    @ExperimentalSerializationApi
    suspend fun spreadFeed(pair : List<String>, webSocketCallback: (result: KrakenAPIWebSocketResponse<SpreadFeed>) -> Unit)

    /*
       get OrderBook data
      */
    @ExperimentalSerializationApi
    suspend fun orderBook(pair : List<String>, depth : Int?=10, webSocketCallback: (result: KrakenAPIWebSocketResponse<OrderBook>) -> Unit)


    /*
       get own trades
       Subscribe to an authenticated channel
   */
    @ExperimentalSerializationApi
    suspend fun ownTrades(token: String, webSocketCallback: (result: KrakenAPIWebSocketResponse<OwnTrades>) -> Unit)


    /*
       get open orders
       Subscribe to an authenticated channel
    */
    @ExperimentalSerializationApi
    suspend fun openOrders(token: String, webSocketCallback: (result: KrakenAPIWebSocketResponse<OpenOrders>) -> Unit)


}