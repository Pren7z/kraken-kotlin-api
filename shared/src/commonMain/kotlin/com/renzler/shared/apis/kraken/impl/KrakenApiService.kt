package com.AKMM.shared.apis.kraken.impl


import com.renzler.shared.apis.kraken.constant.KrakenApiConstants
import com.renzler.shared.apis.kraken.impl.Auth
import com.renzler.shared.apis.kraken.impl.KrakenAPIResponse
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

import kotlinx.serialization.json.Json




class KrakenApiService constructor(val apiKey : String? =null,val apiSecret : String? = null, val otp : String ? = null) {



    val httpClient  = HttpClient() {

        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true; isLenient = true ; }
            serializer = KotlinxSerializer(json)
        }

        install(Logging){
            logger = CustomAndroidHttpLogger
            level = LogLevel.ALL

        }

    }

    suspend fun<T> requestAndCatch(
            block: suspend () -> T
    ): KrakenAPIResponse<T>{
        return try
        {
            KrakenAPIResponse.Success(block())
        }catch (e: Throwable) {
            KrakenAPIResponse.Failure(e)
        }
    }


    suspend inline fun<reified T> authPost(uriPath : String, vararg postData : Pair<String,String>) : KrakenAPIResponse<T> {

        val nonce = Auth().nonce

        return requestAndCatch {
            httpClient.post(KrakenApiConstants.base_URL + uriPath) {

                headers {
                    append("API-Key", apiKey!!)
                    append("API-Sign",Auth().apiSign(nonce,uriPath,apiSecret!!,*postData))
                }

                body = FormDataContent(
                    Parameters.build {
                        append("nonce", nonce)
                        postData.let{
                            for((k,v) in postData.toMap()){
                                append(k,v)
                            }
                        }
                    }
                )

            }
        }

    }


}

object CustomAndroidHttpLogger : Logger {
    private const val logTag = "CustomAndroidHttpLogger"

    override fun log(message: String) {
        println(message)
    }
}




