package com.snacks.lemonsqueezy.api.internal.ktor

import io.ktor.client.request.*
import io.ktor.util.reflect.*

/**
 * Interface for performing HTTP requests.
 */
interface HttpRequester {
    suspend fun <T : Any> performRequest(info: TypeInfo, builder: HttpRequestBuilder.() -> Unit): T
    companion object
}

/**
 * Perform an HTTP request and retrieve a result.
 * @param builder The HttpRequestBuilder that contains the HTTP request details.
 * @return The result of the HTTP request.
 */
internal suspend inline fun <reified T> HttpRequester.performRequest(noinline builder: HttpRequestBuilder.() -> Unit): T {
    return performRequest(typeInfo<T>(), builder)
}