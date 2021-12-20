package com.renzler.shared.apis.kraken.impl

import io.ktor.client.*
import kotlinx.cinterop.*
import platform.CoreCrypto.CC_SHA256
import platform.CoreCrypto.CC_SHA256_DIGEST_LENGTH


actual fun sha256(data : String) :ByteArray {
    val input = data.toUtf8()
    val digest = UByteArray(CC_SHA256_DIGEST_LENGTH)
    input.usePinned { inputPinned ->
        digest.usePinned { digestPinned ->
            CC_SHA256(inputPinned.addressOf(0), input.size.convert(), digestPinned.addressOf(0))
        }
    }
    val digestString = digest.joinToString(separator = "") { it -> it.toString(16) }
    println(digestString)

    return digestString

};


actual fun hmacSha512(data : ByteArray, key : String) :String {

    return data.toString()
};

