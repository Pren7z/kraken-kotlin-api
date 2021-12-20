package com.renzler.shared.apis.kraken



import com.renzler.shared.apis.kraken.constant.CandleStickInterval
import com.renzler.shared.apis.kraken.constant.OrderBookDepth
import com.renzler.shared.apis.kraken.impl.KrakenAPIResponse
import com.renzler.shared.apis.kraken.impl.KrakenAPIWebSocketResponse
import com.renzler.shared.apis.kraken.impl.KrakenApiRestClientImpl
import com.renzler.shared.apis.kraken.impl.KrakenApiWebSocketClientImpl
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi


class Kraken {


    val apiKey = "fiU23o5V7Q/buCG9RxJAZ1XvEY5Jx6vjNbjcjfh7uzhlOqNanKehKunS"
    val apiSecret = "fqkCF6eT4LZ7I1X6e4sWASg2MFoeCUuq+ZfvsO+fAs2U+DNJOMdBgESlOswDTUxz28S4QsqpCAy7lhc3MtIfzA=="

    val krakenApiRestClient = KrakenApiRestClientImpl(apiKey,apiSecret)
    val krakenApiWebSocketClient = KrakenApiWebSocketClientImpl()
    val scope = CoroutineScope(Job() + Dispatchers.Main)


    @ExperimentalSerializationApi
    fun getOpenOrders() =  scope.launch{



      val result = async {
          krakenApiRestClient.getWebSocketToken()


      }.await()

        when(result){

            is KrakenAPIResponse.Success -> krakenApiWebSocketClient.openOrders( result.data.getToken()){

                when(it){
                    is KrakenAPIWebSocketResponse.ChannelData -> println("Success: "+it.data)
                    is KrakenAPIWebSocketResponse.Heartbeat -> println("Heartbeat: "+it.data)
                    is KrakenAPIWebSocketResponse.Subscription -> println("Subscription: "+it.data)
                    is KrakenAPIWebSocketResponse.SystemStatus -> println("SystemStatus: "+it.data)
                    is KrakenAPIWebSocketResponse.Failure -> println("Error: "+it)
                }

            }


            is KrakenAPIResponse.Failure -> println(result.throwable)
        }
    }


    @ExperimentalSerializationApi
    @DelicateCoroutinesApi
    fun getTicker() =  scope.launch{


          krakenApiWebSocketClient.ticker(listOf("XBT/USD")){

             when(it){
                 is KrakenAPIWebSocketResponse.ChannelData -> println("Success: "+it.data)
                 is KrakenAPIWebSocketResponse.Heartbeat -> println("Heartbeat: "+it.data)
                 is KrakenAPIWebSocketResponse.Subscription -> println("Subscription: "+it.data)
                 is KrakenAPIWebSocketResponse.SystemStatus -> println("SystemStatus: "+it.data)
                 is KrakenAPIWebSocketResponse.Failure -> println("Error: "+it)
             
             }
          }



    }






    fun logLong(result : String){
        val maxLogSize = 1000
        result.chunked(maxLogSize).forEach { print(it) }

    }


}