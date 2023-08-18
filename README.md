# Kraken API - Kotlin Multiplatform Mobile

This is a Kotlin Multiplatform Mobile implementaion for interacting with the API (REST & Websocket) of the crypto currency exchange Kraken. It uses Ktor and Kotlinx serialization.
It is written for an Android application and tested with Android devices only.

# Example

REST API (public)

    val krakenApiRestClient = KrakenApiRestClientImpl()
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    @ExperimentalSerializationApi
    fun getOrderBook() =  scope.launch{

        val result = async {
            krakenApiRestClient.getOrderBook("XBTUSD",OrderBookDepth.TEN.depth)
        }.await()

        when(result){
            is KrakenAPIResponse.Success -> println(result.data.getOrderBook())
            is KrakenAPIResponse.Failure -> println(result.throwable)
        }
    }
    
REST API (private with authentication)

    val apiKey = ""
    val apiSecret = ""

    val krakenApiRestClient = KrakenApiRestClientImpl(apiKey,apiSecret)
    val scope = CoroutineScope(Job() + Dispatchers.Main)
    
    @ExperimentalSerializationApi
    fun getOpenOrders() =  scope.launch{

      val result = async {
          krakenApiRestClient.getOpenOrders()
      }.await()

        when(result){
            is KrakenAPIResponse.Success -> println(result.data.openOrdersList)
            is KrakenAPIResponse.Failure -> println(result.throwable)
        }
    }
    
Websocket API

    val krakenApiWebSocketClient = KrakenApiWebSocketClientImpl()
    val scope = CoroutineScope(Job() + Dispatchers.Main)
    
    @ExperimentalSerializationApi
    @DelicateCoroutinesApi
    fun getTicker() =  scope.launch{
    
          krakenApiWebSocketClient.ticker(listOf("XBT/USD")){

             when(it){
                 is KrakenAPIWebSocketResponse.ChannelData -> println(it.data)
                 is KrakenAPIWebSocketResponse.Heartbeat -> println(it.data)
                 is KrakenAPIWebSocketResponse.Subscription -> println(it.data)
                 is KrakenAPIWebSocketResponse.SystemStatus -> println(it.data)
                 is KrakenAPIWebSocketResponse.Failure -> println(it)
             }
          }
    }
