package com.renzler.shared.apis.kraken.constant

enum class Ordertype(val ordertype: String) {

    MARKET("market"),
    LIMIT("limit"),
    STOPLOSS("stop-loss"),
    TAKEPROFIT("take-profit"),
    STOPLOSSLIMIT("stop-loss-limit"),
    TAKEPROFITLIMIT("take-profit-limit"),
    SETTLEPOSITION("settle-position")

}