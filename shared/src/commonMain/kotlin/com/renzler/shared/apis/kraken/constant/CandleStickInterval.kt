package com.renzler.shared.apis.kraken.constant

enum class CandleStickInterval(val timeunit: Int) {
    ONE_MINUTE(1),
    FIVE_MINUTES(5),
    FIFTEEN_MINUTES(15),
    HALF_HOURLY(30),
    HOURLY(60),
    FOUR_HOURLY(240),
    DAILY(1440),
    WEEKLY(10080),
    TWO_WEEKLY(21600)
}