package com.snacks.lemonsqueezy.api.internal.ktor

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.reflect.*


/**
 * Interface for performing HTTP requests.
 */
internal interface HttpRequester {

    /**
     * Perform an HTTP request and retrieve a result.
     *
     * @param info The type information of the result.
     * @param block The suspend function that performs the HTTP request using the HttpClient and returns the HttpResponse.
     * @return The result of the HTTP request.
     */
    suspend fun <T : Any> performRequest(info: TypeInfo, block: suspend (HttpClient) -> HttpResponse): T

    /**
     * Perform an HTTP request and retrieve a result.
     *
     * @param builder The HttpRequestBuilder that contains the HTTP request details.
     * @param block The suspend function that takes the HttpResponse as a parameter and returns the result.
     */
    suspend fun <T : Any> performRequest(
        builder: HttpRequestBuilder,
        block: suspend (response: HttpResponse) -> T,
    )

}

/**
 * Perform an HTTP request and retrieve a result.
 *
 * @param block The suspend function that performs the HTTP request using the HttpClient and returns the HttpResponse.
 * @return The result of the HTTP request.
 */
internal suspend inline fun <reified T> HttpRequester.performRequest(noinline block: suspend (HttpClient) -> HttpResponse): T {
    return performRequest(typeInfo<T>(), block)
}