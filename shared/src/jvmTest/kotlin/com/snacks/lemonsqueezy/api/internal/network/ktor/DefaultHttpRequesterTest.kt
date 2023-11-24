package com.snacks.lemonsqueezy.api.internal.network.ktor

import com.snacks.lemonsqueezy.api.mockHttpClient
import com.snacks.lemonsqueezy.api.internal.network.ktor.DefaultHttpRequester
import com.snacks.lemonsqueezy.api.internal.network.ktor.HttpRequester
import com.snacks.lemonsqueezy.api.internal.network.ktor.default
import com.snacks.lemonsqueezy.api.internal.network.ktor.performRequest
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class DefaultHttpRequesterTest {
    private lateinit var httpClient: HttpClient

    @Test
    fun `should return activation success when activated`() = runBlocking {
        val mockResponse = """
            {
                "activated": true,
                "error": null,
                "license_key": {
                    "id": 1,
                    "status": "active",
                    "key": "some_license_key",
                    "activation_limit": 1,
                    "activation_usage": 5,
                    "created_at": "2021-03-25 11:10:18",
                    "expires_at": null
                },
                "instance": {
                    "id": "some-instance-id",
                    "name": "Test",
                    "created_at": "2021-04-06 14:08:46"
                },
                "meta": {
                    "store_id": 1,
                    "order_id": 2,
                    "order_item_id": 3,
                    "product_id": 4,
                    "product_name": "Example Product",
                    "variant_id": 5,
                    "variant_name": "Default",
                    "customer_id": 6,
                    "customer_name": "some-customer-name",
                    "customer_email": "some-customer-email"
                }
            }
        """.trimIndent()

        httpClient = mockHttpClient(mockResponse)

        val requester = DefaultHttpRequester(httpClient)

        val result = requester.performRequest<String> {
            url(path = "/v1/licenses/activate")
        }

        assertEquals(mockResponse, result)
    }

    @Test
    fun `should return activation error when not activated`() = runBlocking {
        val mockResponse = """
            {
                "activated": false,
                "error": "some-error",
                "license_key": {
                    "id": 1,
                    "status": "active",
                    "key": "some-license-key",
                    "activation_limit": 1,
                    "activation_usage": 5,
                    "created_at": "2021-03-25 11:10:18",
                    "expires_at": null
                },
                "meta": {
                    "store_id": 1,
                    "order_id": 2,
                    "order_item_id": 3,
                    "product_id": 4,
                    "product_name": "Example Product",
                    "variant_id": 5,
                    "variant_name": "Default",
                    "customer_id": 6,
                    "customer_name": "some-customer-name",
                    "customer_email": "some-customer-email"
                }
            }
        """.trimIndent()

        httpClient = mockHttpClient(mockResponse)

        val requester = HttpRequester.default(httpClient)

        val result = requester.performRequest<String> {
            url(path = "/v1/licenses/activate")
        }

        assertEquals(mockResponse, result)
    }
}