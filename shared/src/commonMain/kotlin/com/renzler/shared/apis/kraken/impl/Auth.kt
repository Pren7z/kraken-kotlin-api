package com.renzler.shared.apis.kraken.impl

import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.utils.io.core.*
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class Auth {

    val nonce: String = Clock.System.now().toEpochMilliseconds().toString()


    fun apiSign(nonce : String, uriPath : String , apiSecret : String ,vararg postData : Pair<String,String>) : String {

        val data =urlEncodePostData(Pair("nonce",nonce),*postData)
        val message = uriPath.toByteArray()+sha256( nonce+data);

        return hmacSha512(message,apiSecret)
    }

    fun urlEncodePostData (vararg postData : Pair<String,String>) : String{

        return FormDataContent(Parameters.build {
            for ((k, v) in postData.toMap()) {
                append(k, v)
            }
        }).formData.formUrlEncode()

    }



}