package com.renzler.shared.apis.kraken

import com.AKMM.shared.apis.kraken.domain.*
import com.renzler.shared.apis.kraken.constant.Tradetype
import com.renzler.shared.apis.kraken.domainREST.*
import com.renzler.shared.apis.kraken.impl.KrakenAPIResponse


interface KrakenApiRestClient {

    /*
    Get a list of all traded assets
    */
    suspend fun getAllAssets() : KrakenAPIResponse<AssetInfo>

    /*
    Get a list of all asset pairs
   */
    suspend fun getAllAssetPairs() : KrakenAPIResponse<AssetPairs>

    /*
    Get the ticker of a pair
    @param pair (e.g. XBTUSD)
   */
    suspend fun getTicker(pair : String) : KrakenAPIResponse<TickerInformation>

    /*
       Get the ticker of a pair
       @param pair (e.g. XBTUSD)
       @param interval (e.g. default 3600) See Enum CandleStickInterval.class
       @param since represents a unix timstamp (e.g. 1626785422)
      */
    suspend fun getCandleSticks(pair : String,
                                since : Long? = null,
                                interval : Int? = 3600) : KrakenAPIResponse<Candlesticks>

    /*
       Get the order book of a pair
       @param ticker (e.g. XBTUSD)
       @param count (e.g. default 100) integer [ 1 .. 500 ]
      */
    suspend fun getOrderBook(pair : String,
                             count : Int? = 100) : KrakenAPIResponse<OrderBook>


    /*
      Get the user account *Auth required*
    */
    suspend fun getAccountBalance() : KrakenAPIResponse<Account>


    /*
     Get Open Orders *Auth required*
       @param  trades ( Default: false, Whether or not to include trades related to position in output)
    */
    suspend fun getOpenOrders(trades: Boolean ? = false) : KrakenAPIResponse<OpenOrder>



    /*
       Place a order *Auth required*

       @param  type     (Default: "all", see ENUM class Tradetype)
       @param  trades  (Default: false; Whether or not to include trades related to position in output)
       @param  start  (Starting unix timestamp or trade tx ID of results (exclusive))
       @param  end  (Ending unix timestamp or trade tx ID of results (inclusive))
       @param  ofs  (Result offset for pagination)
      */

    suspend fun getTrades(type : String?= Tradetype.ALL.tradetype,
                          trades : Boolean?= false,
                          start : String?= null,
                          end : String?= null,
                          ofs: String?= null): KrakenAPIResponse<Trades>



    /*
       Place an order *Auth required*
       @param  ordertype -- required -- (see ENUM class Ordertype)
       @param  pair      -- required --  (e.g. XBTUSD)
       @param  price ( - Limit price for limit orders
                        - Trigger price for stop-loss, stop-loss-limit, take-profit and take-profit-limit orders)
       @param  type     -- required -- (see ENUM class Type)
       @param  volume  (Order quantity in terms of the base asset e.g. 1.25)
       @param  price2  (Limit price for stop-loss-limit and take-profit-limit orders)
       @param  closeOrdertype  (Conditional close order type)
       @param  closePrice  (Conditional close order price)
       @param  closePrice2  (Conditional close order price2)
      */

    suspend fun addOrder(ordertype : String,
                         pair : String,
                         price : String?,
                         type : String,
                         volume: String ,
                         price2: String ?= null ,
                         closeOrdertype : String?= null,
                         closePrice : String? = null,
                         closePrice2: String?= null) : KrakenAPIResponse<NewOrder>


    /*
       Cancel an order *Auth required*
       @param  txid      -- required --  (Open order transaction ID (txid) or user reference (userref))

      */

    suspend fun cancelOrder(txid : String) : KrakenAPIResponse<CancelOrder>

    /*
         Get Deposit Address of an asset *Auth required*
         @param  asset -- required -- (Asset being deposited)
         @param  method      -- required --  (Name of the deposit method e.g. BITCOIN)
         @param  new ( Default: false, Whether or not to generate a new address)

        */

    suspend fun getDepositAddress(asset : String,
                                  method : String,
                                  new : Boolean? = false) : KrakenAPIResponse<DepositAddresses>

    /*
       Get Deposit Address of an asset *Auth required*
       @param  asset -- required -- (Asset being deposited)
       @param  method      -- required --  (Name of the deposit method e.g. BITCOIN)
       @param  new ( Default: false, Whether or not to generate a new address)

      */

    suspend fun getWebSocketToken() : KrakenAPIResponse<WebSocketToken>


}