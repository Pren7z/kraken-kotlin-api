package com.renzler.shared.apis.kraken.impl


import com.renzler.shared.apis.kraken.constant.KrakenApiConstants
import com.renzler.shared.apis.kraken.domainWS.Heartbeat

import io.ktor.client.*

import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString


import kotlinx.serialization.json.*


class KrakenApiWebSocketService(val json: Json)  {



    val webSocketClient = HttpClient(CIO) {
        install(WebSockets)
    }




    @ExperimentalSerializationApi
    suspend  inline fun<reified T> webSocketRequest(
        url:String,
        message: String,
        crossinline webSocketCallback: (result: KrakenAPIWebSocketResponse<T>) ->  Unit,
        crossinline block: (String) -> T,
    ){

            webSocketClient.webSocket(
                HttpMethod.Get,
                url,
                DEFAULT_PORT,
                "/"
            ) {

                sendMessage(this,message,webSocketCallback,block)

            }


    }





    @ExperimentalSerializationApi
    suspend inline fun<reified T> sendMessage(
        webSocketSession: WebSocketSession,
        message: String,
        crossinline webSocketCallback: (result: KrakenAPIWebSocketResponse<T>) -> Unit,
        crossinline block: (String) -> T,
    ){


        webSocketSession.send(message)

            while (true) {

                try{
                    webSocketSession.incoming.consumeAsFlow()
                        .mapNotNull { it as? Frame.Text }
                        .map { it.readText() }
                        .collect {

                            webSocketCallback.invoke (
                               collectAndCatch (it,{block(it)})
                            )

                        }
                }catch(e: Throwable) {
                   webSocketCallback.invoke(KrakenAPIWebSocketResponse.Failure(e))
                }
            }
    }

    @ExperimentalSerializationApi
    inline fun<reified T> collectAndCatch(
        response:String,
        block: () -> T
    ): KrakenAPIWebSocketResponse<T>{
       try
        {
            return KrakenAPIWebSocketResponse.ChannelData(block())
        }catch (throwable: Throwable) {
            return getEvents<T>(response,throwable)
        }
    }



    @ExperimentalSerializationApi
     fun< T> getEvents(response:String, throwable:Throwable):KrakenAPIWebSocketResponse<T> {

         val payload: JsonObject = json.decodeFromString(response)
         val event :String = payload.get(KrakenApiConstants.EVENT).toString()

         when (event.replace("\"", "")) {
            KrakenApiConstants.SYSTEMSTATUS -> return KrakenAPIWebSocketResponse.SystemStatus(json.decodeFromString(response))
            KrakenApiConstants.SUBSCRIPTIONSTATUS -> return KrakenAPIWebSocketResponse.Subscription(json.decodeFromString(response))
            KrakenApiConstants.HEARTBEAT -> return KrakenAPIWebSocketResponse.Heartbeat(json.decodeFromString(response))
          }

        return KrakenAPIWebSocketResponse.Failure(throwable)
    }

}



