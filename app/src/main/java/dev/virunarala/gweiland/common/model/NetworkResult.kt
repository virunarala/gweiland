package dev.virunarala.gweiland.common.model

sealed class NetworkResult {

    object NotInitialized: NetworkResult()

    object Loading: NetworkResult()

    object NoInternet: NetworkResult()

    data class Error(val message: String): NetworkResult()

    data class Success<out T>(val data: T): NetworkResult()

    override fun toString(): String {
        return when(this) {
            is NotInitialized -> "Not Initialized"
            is Loading -> "Loading"
            is NoInternet -> "No Internet"
            is Error -> "Error: $message"
            is Success<*> -> "Success: $data"
        }
    }

    fun getErrorMessage(): String {
        val errorMessage: String = if(this is Error) message else "NA"
        return errorMessage
    }

}