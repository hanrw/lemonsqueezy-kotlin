package com.snacks.lemonsqueezy.api

import io.ktor.client.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class LicenseApiTest {
    private lateinit var httpClient: HttpClient

    @Test
    fun `should return activation error when not activated`() = runBlocking {
        httpClient = mockHttpClient(
            """
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
        )

        val expectedResponse = LicenseActivationErrorResponse(
            activated = false,
            error = "some-error",
            licenseKey = LicenseKey(
                id = 1,
                status = "active",
                key = "some-license-key",
                activationLimit = 1,
                activationUsage = 5,
                createdAt = "2021-03-25 11:10:18",
                expiresAt = null
            ),
            meta = Meta(
                storeId = 1,
                orderId = 2,
                orderItemId = 3,
                productId = 4,
                productName = "Example Product",
                variantId = 5,
                variantName = "Default",
                customerId = 6,
                customerName = "some-customer-name",
                customerEmail = "some-customer-email"
            )
        )

        val api = LemonSqueezyLicenseApi(httpClient)
        val licenseKey = "your_license_key"
        val instanceName = "your_instance_name"

        val result = api.activeLicense(licenseKey, instanceName)

        assertEquals(false, result.isSuccess())
        assertEquals(expectedResponse, result)
    }

    @Test
    fun `should return activation success when activated`() = runBlocking {
        httpClient = mockHttpClient(
            """
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
        )

        val expectedResponse = LicenseActivationSuccessResponse(
            activated = true,
            licenseKey = LicenseKey(
                id = 1,
                status = "active",
                key = "some_license_key",
                activationLimit = 1,
                activationUsage = 5,
                createdAt = "2021-03-25 11:10:18",
                expiresAt = null
            ),
            instance = Instance(
                id = "some-instance-id",
                name = "Test",
                createdAt = "2021-04-06 14:08:46"
            ),
            meta = Meta(
                storeId = 1,
                orderId = 2,
                orderItemId = 3,
                productId = 4,
                productName = "Example Product",
                variantId = 5,
                variantName = "Default",
                customerId = 6,
                customerName = "some-customer-name",
                customerEmail = "some-customer-email"
            )
        )

        val api = LemonSqueezyLicenseApi(httpClient)
        val licenseKey = "your_license_key"
        val instanceName = "your_instance_name"

        val result = api.activeLicense(licenseKey, instanceName)

        assertEquals(true, result.isSuccess())
        assertEquals(expectedResponse, result)
    }
}

