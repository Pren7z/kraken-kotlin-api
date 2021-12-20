package com.renzler.shared.apis.kraken.impl

import com.renzler.shared.apis.kraken.domainWS.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*

class KrakenApiWebSocketPayload(val json: Json) {



    @ExperimentalSerializationApi
    fun getTicker(response:String): Ticker {



        val tickerArray : JsonArray = json.decodeFromString(response)
        val tickerObject : JsonObject = tickerArray[1].jsonObject

        val ask : JsonArray = tickerObject.get("a")!!.jsonArray
        val bid : JsonArray = tickerObject.get("b")!!.jsonArray
        val close : JsonArray = tickerObject.get("c")!!.jsonArray
        val volume : JsonArray = tickerObject.get("h")!!.jsonArray
        val volumeWeightedAveragePrice : JsonArray = tickerObject.get("l")!!.jsonArray
        val numberOfTrades : JsonArray = tickerObject.get("o")!!.jsonArray
        val lowPrice : JsonArray = tickerObject.get("p")!!.jsonArray
        val highPrice : JsonArray = tickerObject.get("t")!!.jsonArray
        val openPrice : JsonArray = tickerObject.get("v")!!.jsonArray


        return Ticker(tickerArray[0].toString(),tickerArray[2].toString(),tickerArray[3].toString(),
                    TickerData(
                        Ask(ask[0].toString(),ask[1].toString().toInt(),ask[2].toString()),
                        Bid(bid[0].toString(),bid[1].toString().toInt(),bid[2].toString()),
                        Close(close[0].toString(),close[1].toString()),
                        Volume(volume[0].toString(),volume[1].toString()),
                        VolumeWeightedAveragePrice(volumeWeightedAveragePrice[0].toString(),volumeWeightedAveragePrice[1].toString()),
                        NumberOfTrades(numberOfTrades[0].toString(),numberOfTrades[1].toString()),
                        LowPrice(lowPrice[0].toString(),lowPrice[1].toString()),
                        HighPrice(highPrice[0].toString(),highPrice[1].toString()),
                        OpenPrice(openPrice[0].toString(),openPrice[1].toString())

                    )
        )

    }

    @ExperimentalSerializationApi
    fun getCandles(response:String): OHLC {

        val ohlcArray : JsonArray = json.decodeFromString(response)
        val ohlc : JsonArray = json.decodeFromString(ohlcArray[1].toString())

        return OHLC(ohlcArray[0].toString(),ohlcArray[2].toString(),ohlcArray[3].toString(),
            Candles(
                ohlc[0].toString(),
                ohlc[1].toString(),
                ohlc[2].toString(),
                ohlc[3].toString(),
                ohlc[4].toString(),
                ohlc[5].toString(),
                ohlc[6].toString(),
                ohlc[7].toString(),
                ohlc[8].toString().toInt()
            )
        )

    }


    @ExperimentalSerializationApi
    fun getTradeFeed(response:String): TradeFeed {

        val tradeFeedArray : JsonArray = json.decodeFromString(response)
        val feed : JsonArray = json.decodeFromString(tradeFeedArray[1].toString())

        return TradeFeed(tradeFeedArray[0].toString(),tradeFeedArray[2].toString(),tradeFeedArray[3].toString(),
            Feed(
                feed[0].toString(),
                feed[1].toString(),
                feed[2].toString(),
                feed[3].toString(),
                feed[4].toString(),
                feed[5].toString()
            )
        )

    }


    @ExperimentalSerializationApi
    fun getSpreadFeed(response:String): SpreadFeed {

        val spreadFeedArray : JsonArray = json.decodeFromString(response)
        val feed : JsonArray = json.decodeFromString(spreadFeedArray[1].toString())

        return SpreadFeed(spreadFeedArray[0].toString(),spreadFeedArray[2].toString(),spreadFeedArray[3].toString(),
            Spread(
                feed[0].toString(),
                feed[1].toString(),
                feed[2].toString(),
                feed[3].toString(),
                feed[4].toString()
            )
        )

    }

    @ExperimentalSerializationApi
    fun getOrderBook(response:String): OrderBook {


        val bookArray : JsonArray = json.decodeFromString(response)
        val bookObject1 : JsonObject = bookArray[1].jsonObject

        /*
        Conditionals are necessary since the Kraken API response's structure has multiple possibilities
         */

        if(bookArray.size<5){
            when(bookObject1.keys.toString()){
                "[as, bs]" ->
                    return OrderBook(bookArray[0].toString(),bookArray[2].toString(),bookArray[3].toString(),
                        orderBookSnapshot = json.decodeFromString(bookObject1.toString())
                    )


                else -> {
                    return OrderBook(
                        bookArray[0].toString(), bookArray[2].toString(), bookArray[3].toString(),
                        orderBookUpdate = json.decodeFromString(bookObject1.toString())
                    )
                }
            }



        }else{
            val bookObject2 : JsonObject = bookArray[2].jsonObject
            return OrderBook(
                bookArray[0].toString(), bookArray[2].toString(), bookArray[3].toString(),
                orderBookUpdate = json.decodeFromString("$bookObject1,$bookObject2")
            )

        }



    }


    @ExperimentalSerializationApi
    fun getOwnTrades(response:String): OwnTrades {

        val tradesArray : JsonArray = json.decodeFromString(response)

        return OwnTrades(tradesArray[1].toString(),tradesArray[2].jsonObject.get("sequence").toString().toInt(),
            json.decodeFromString(tradesArray[0].toString())

        )
    }


    @ExperimentalSerializationApi
    fun getOpenOrders(response:String): OpenOrders {

        val tradesArray : JsonArray = json.decodeFromString(response)

        return OpenOrders(tradesArray[1].toString(),tradesArray[2].jsonObject.get("sequence").toString().toInt(),
            json.decodeFromString(tradesArray[0].toString())

        )
    }


}