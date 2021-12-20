package com.renzler.shared.apis.kraken.impl


import com.google.common.hash.Hashing
import io.ktor.client.*

import java.io.UnsupportedEncodingException
import java.nio.charset.StandardCharsets


import java.util.*
import javax.crypto.Mac


import javax.crypto.spec.SecretKeySpec



actual fun sha256(data : String):ByteArray {

    return Hashing.sha256().hashString(data, StandardCharsets.UTF_8).asBytes()
}

actual fun hmacSha512(data : ByteArray, key: String):String {


    try {
        val HMAC_SHA512 = "HmacSHA512"
        val sha512Hmac = Mac.getInstance(HMAC_SHA512)
        val keySpec = SecretKeySpec( Base64.getDecoder().decode(key.toByteArray(StandardCharsets.UTF_8)), HMAC_SHA512)

        sha512Hmac.init(keySpec)

        val macData = sha512Hmac.doFinal(data)

        return Base64.getEncoder().encodeToString(macData)

    } catch (e: UnsupportedEncodingException) {
        throw RuntimeException(e)
    }

}

