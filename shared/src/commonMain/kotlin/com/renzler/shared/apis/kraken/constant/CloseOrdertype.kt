package com.renzler.shared.apis.kraken.constant

enum class CloseOrdertype(val closeOrdertype: String) {
    LIMIT("limit"),
    STOPLOSS("stop-loss"),
    TAKEPROFIT("take-profit"),
    STOPLOSSLIMIT("stop-loss-limit"),
    TAKEPROFITLIMIT("take-profit-limit"),


}