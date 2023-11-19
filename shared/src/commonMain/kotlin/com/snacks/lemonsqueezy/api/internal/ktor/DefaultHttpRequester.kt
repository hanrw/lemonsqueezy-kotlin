package com.snacks.lemonsqueezy.api.internal.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.reflect.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.CancellationException

/**
 * Default implementation of [HttpRequester].
 * @property httpClient The HttpClient to use for performing HTTP requests.
 */
internal class DefaultHttpRequester(private val httpClient: HttpClient) : HttpRequester {

    /**
     * Perform an HTTP request and get a result
     *
     * @param info The TypeInfo for the desired response type
     * @param block A suspend function that takes an HttpClient and returns an HttpResponse
     * @return The result of the HTTP request as the specified response type
     * @throws Exception if any exceptions occur during the HTTP request
     */
    override suspend fun <T : Any> performRequest(info: TypeInfo, block: suspend (HttpClient) -> HttpResponse): T {
        try {
            val response = block(httpClient)
            return response.body(info)
        } catch (e: Exception) {
            throw handleException(e)
        }
    }

    /**
     * Perform an HTTP request
     *
     * @param builder The HttpRequestBuilder containing the request configuration
     * @param block A suspend function that takes an HttpResponse and returns a result of type T
     * @throws Exception if any exceptions occur during the HTTP request
     */
    override suspend fun <T : Any> performRequest(
        builder: HttpRequestBuilder,
        block: suspend (response: HttpResponse) -> T,
    ) {
        try {
            HttpStatement(builder = builder, client = httpClient).execute(block)
        } catch (e: Exception) {
            throw handleException(e)
        }
    }

    /**
     * Handles various exceptions that can occur during an API request.
     *
     * @param e The exception that occurred during the API request
     * @return The exception to be thrown
     */
    private fun handleException(e: Throwable) = when (e) {
        is CancellationException -> e // propagate coroutine cancellation
        is ClientRequestException -> e
        is ServerResponseException -> e
        is IOException -> e
        else -> e
    }
}
