package com.renzler.shared.apis.kraken.impl

import com.AKMM.shared.apis.kraken.domain.*
import com.AKMM.shared.apis.kraken.impl.KrakenApiService
import com.renzler.shared.apis.kraken.KrakenApiRestClient
import com.renzler.shared.apis.kraken.constant.KrakenApiConstants
import com.renzler.shared.apis.kraken.domainREST.*

import io.ktor.client.request.*



class KrakenApiRestClientImpl constructor(val apiKey : String? =null,val apiSecret : String? = null, val otp : String ? = null)  : KrakenApiRestClient {


    private val krakenApiService = KrakenApiService(apiKey,apiSecret,otp);
    private val httpClient = krakenApiService.httpClient;
    private val krakenApiConstants = KrakenApiConstants.Companion


    override suspend fun getAllAssets(): KrakenAPIResponse<AssetInfo> {
        return krakenApiService.requestAndCatch { httpClient.get(krakenApiConstants.base_URL + "/0/public/Assets") }
    }

    override suspend fun getAllAssetPairs(): KrakenAPIResponse<AssetPairs> {
        return krakenApiService.requestAndCatch { httpClient.get(krakenApiConstants.base_URL + "/0/public/AssetPairs") }
    }

    override suspend fun getTicker(pair: String): KrakenAPIResponse<TickerInformation> {
        return krakenApiService.requestAndCatch {
            httpClient.get(krakenApiConstants.base_URL + "/0/public/Ticker") {
                parameter("pair", pair)
            }
        }
    }

    override suspend fun getCandleSticks(
        pair: String,
        since: Long?,
        interval: Int?
    ): KrakenAPIResponse<Candlesticks> {
        return krakenApiService.requestAndCatch {
            httpClient.get(krakenApiConstants.base_URL + "/0/public/OHLC") {
                parameter("pair", pair)
                parameter("interval", interval)
                parameter("since", since)
            }
        }
    }

    override suspend fun getOrderBook(pair: String, count: Int?): KrakenAPIResponse<OrderBook> {
        return krakenApiService.requestAndCatch {
            httpClient.get(krakenApiConstants.base_URL + "/0/public/Depth") {
                parameter("pair", pair)
                parameter("count", count)
            }
        }
    }

    override suspend fun getAccountBalance(): KrakenAPIResponse<Account> {

        return krakenApiService.authPost("/0/private/Balance")

    }

    override suspend fun getOpenOrders(trades: Boolean?): KrakenAPIResponse<OpenOrder> {
        return krakenApiService.authPost("/0/private/OpenOrders",
            *toPostBody(
                Pair("trades",trades.toString())
            )
        )
    }

    override suspend fun getTrades(type: String?, trades: Boolean?, start: String?, end: String?, ofs: String?
    ): KrakenAPIResponse<Trades> {
        return krakenApiService.authPost("/0/private/TradesHistory",

            *toPostBody(
                Pair("type",type),
                Pair("trades",trades?.toString()),
                Pair("start",start),
                Pair("end",end),
                Pair("ofs",ofs),
            )
        )

    }


    override suspend fun addOrder(ordertype : String,  pair : String, price : String?,type : String, volume: String , price2:String ? ,
                                  closeOrdertype : String?, closePrice : String? , closePrice2: String?): KrakenAPIResponse<NewOrder> {

           return krakenApiService.authPost("/0/private/AddOrder",
               *toPostBody(
                   Pair("ordertype",ordertype),
                   Pair("pair",pair),
                   Pair("price",price),
                   Pair("type",type),
                   Pair("volume",volume),
                   Pair("price2",price2),
                   Pair("closeOrdertype",closeOrdertype),
                   Pair("closePrice",closePrice),
                   Pair("closePrice2",closePrice2),
               )
           )
        }

    override suspend fun cancelOrder(txid: String): KrakenAPIResponse<CancelOrder> {
        return krakenApiService.authPost("/0/private/CancelOrder",
            *toPostBody(
                Pair("txid",txid),
            )
        )
    }

    override suspend fun getDepositAddress(asset: String, method: String, new: Boolean?
    ): KrakenAPIResponse<DepositAddresses> {
        return krakenApiService.authPost("/0/private/CancelOrder",
            *toPostBody(
                Pair("asset",asset),
                Pair("method",method),
                Pair("new",new?.toString())
            )
        )
    }

    override suspend fun getWebSocketToken(): KrakenAPIResponse<WebSocketToken> {
        return krakenApiService.authPost("/0/private/GetWebSocketsToken")
    }


    fun toPostBody(vararg postData : Pair<String,String?>) : Array< Pair<String,String>>{
        val postBody = mutableListOf<Pair<String,String>>()

        postData.forEach{
            if(it.second!=null) {
                postBody.add(it as Pair<String, String>)
            }
        }

        return postBody.toTypedArray()
    }

}