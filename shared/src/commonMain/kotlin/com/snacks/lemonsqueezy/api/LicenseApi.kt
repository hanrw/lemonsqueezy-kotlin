package com.snacks.lemonsqueezy.api

import com.snacks.lemonsqueezy.api.internal.ktor.HttpRequester
import com.snacks.lemonsqueezy.api.internal.ktor.performRequest
import io.ktor.client.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

interface LicenseApi {
    suspend fun activeLicense(licenseKey: String, instanceName: String): LicenseActivationResult

    companion object
}

internal class LemonSqueezyLicenseApi(
    private val requester: HttpRequester,
) : LicenseApi {
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun activeLicense(licenseKey: String, instanceName: String): LicenseActivationResult {
        try {
            val activationResult = requester.performRequest<String> {
                it.post {
                    url(path = "/v1/licenses/activate")
                    setBody(
                        LicenseActivationRequest(
                            licenseKey = licenseKey,
                            instanceName = instanceName,
                        )
                    )
                }
            }

            return try {
                json.decodeFromString<LicenseActivationSuccessResponse>(activationResult)
            } catch (ex: Exception) {
                Json.decodeFromString<LicenseActivationErrorResponse>(activationResult)
            }
        } catch (e: Exception) {
            throw e
        }
    }
}

sealed class LicenseActivationResult {
    @OptIn(ExperimentalContracts::class)
    fun isSuccess(): Boolean {
        contract {
            returns(true) implies (this@LicenseActivationResult is LicenseActivationSuccessResponse)
            returns(false) implies (this@LicenseActivationResult is LicenseActivationErrorResponse)
        }
        return this is LicenseActivationSuccessResponse
    }
}

@Serializable
data class LicenseActivationRequest(
    @SerialName("api_key")
    val licenseKey: String,
    @SerialName("instance_name")
    val instanceName: String,
)

@Serializable
data class LicenseActivationSuccessResponse(
    val activated: Boolean,
    @SerialName("license_key")
    val licenseKey: LicenseKey,
    val instance: Instance,
    val meta: Meta,
) : LicenseActivationResult()

@Serializable
data class LicenseActivationErrorResponse(
    val activated: Boolean,
    val error: String?,
    @SerialName("license_key")
    val licenseKey: LicenseKey,
    val meta: Meta,
) : LicenseActivationResult()

@Serializable
data class LicenseKey(
    val id: Int,
    val status: String,
    val key: String,
    @SerialName("activation_limit")
    val activationLimit: Int,
    @SerialName("activation_usage")
    val activationUsage: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("expires_at")
    val expiresAt: String?,
) {
    companion object {
        fun mock() = LicenseKey(
            id = 1,
            status = "active",
            key = "your_license_key",
            activationLimit = 1,
            activationUsage = 0,
            createdAt = "2021-01-01T00:00:00.000Z",
            expiresAt = null,
        )
    }
}

@Serializable
data class Instance(
    val id: String,
    val name: String,
    @SerialName("created_at")
    val createdAt: String,
) {
    companion object {
        fun mock() = Instance(
            id = "your_instance_id",
            name = "your_instance_name",
            createdAt = "2021-01-01T00:00:00.000Z",
        )
    }
}

@Serializable
data class Meta(
    @SerialName("store_id")
    val storeId: Int,
    @SerialName("order_id")
    val orderId: Int,
    @SerialName("order_item_id")
    val orderItemId: Int,
    @SerialName("product_id")
    val productId: Int,
    @SerialName("product_name")
    val productName: String,
    @SerialName("variant_id")
    val variantId: Int,
    @SerialName("variant_name")
    val variantName: String,
    @SerialName("customer_id")
    val customerId: Int,
    @SerialName("customer_name")
    val customerName: String,
    @SerialName("customer_email")
    val customerEmail: String,
) {
    companion object {
        fun mock() = Meta(
            storeId = 1,
            orderId = 1,
            orderItemId = 1,
            productId = 1,
            productName = "your_product_name",
            variantId = 1,
            variantName = "your_variant_name",
            customerId = 1,
            customerName = "your_customer_name",
            customerEmail = "your_customer_email",
        )
    }
}
