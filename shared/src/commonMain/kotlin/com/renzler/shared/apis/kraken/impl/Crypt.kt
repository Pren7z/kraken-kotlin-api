package com.renzler.shared.apis.kraken.impl

import io.ktor.client.*

expect fun sha256(data : String) : ByteArray

expect fun hmacSha512(data : ByteArray, key : String) : String
