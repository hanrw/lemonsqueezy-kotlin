package com.snacks.lemonsqueezy.api.internal.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.util.reflect.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.CancellationException

/**
 * Default implementation of [HttpRequester].
 * @property httpClient The HttpClient to use for performing HTTP requests.
 */
internal class DefaultHttpRequester(private val httpClient: HttpClient) : HttpRequester {

    override suspend fun <T : Any> performRequest(info: TypeInfo, builder: HttpRequestBuilder.() -> Unit): T {
        try {
            val response = httpClient.request(builder)
            return response.body(info)
        } catch (e: Exception) {
            throw handleException(e)
        }
    }

    /**
     * Handle an exception that occurred while performing an HTTP request.
     * @param e The exception that occurred.
     * @return The exception to propagate.
     */
    private fun handleException(e: Throwable) = when (e) {
        is CancellationException -> e // Do not wrap cancellation exceptions.
        is ClientRequestException -> e
        is ServerResponseException -> e
        is IOException -> e
        else -> e
    }
}
