package com.snacks.lemonsqueezy.api.license

import com.snacks.lemonsqueezy.api.internal.network.ktor.HttpRequester
import com.snacks.lemonsqueezy.api.internal.network.ktor.performRequest
import com.snacks.lemonsqueezy.api.license.request.LicenseActivationRequest
import com.snacks.lemonsqueezy.api.license.request.LicenseDeactivationRequest
import com.snacks.lemonsqueezy.api.license.response.LicenseActivationErrorResponse
import com.snacks.lemonsqueezy.api.license.response.LicenseActivationResult
import com.snacks.lemonsqueezy.api.license.response.LicenseActivationSuccessResponse
import com.snacks.lemonsqueezy.api.license.response.LicenseDeactivationResponse
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

interface LicenseKeysApi {
    suspend fun activeLicense(licenseKey: String, instanceName: String): LicenseActivationResult

    suspend fun deactivateLicense(licenseKey: String, instanceId: String): LicenseDeactivationResponse
}

internal class LemonSqueezyLicenseKeysApi(
    private val requester: HttpRequester,
) : LicenseKeysApi {
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun activeLicense(licenseKey: String, instanceName: String): LicenseActivationResult {
        val activationResult = requester.performRequest<String> {
            method = HttpMethod.Post
            url(path = "/v1/licenses/activate")
            setBody(
                LicenseActivationRequest(
                    licenseKey = licenseKey,
                    instanceName = instanceName,
                )
            )
        }
        return try {
            json.decodeFromString<LicenseActivationSuccessResponse>(activationResult)
        } catch (ex: Exception) {
            Json.decodeFromString<LicenseActivationErrorResponse>(activationResult)
        }
    }

    override suspend fun deactivateLicense(licenseKey: String, instanceId: String): LicenseDeactivationResponse {
        return requester.performRequest<LicenseDeactivationResponse> {
            method = HttpMethod.Post
            url(path = "/v1/licenses/deactivate")
            setBody(
                LicenseDeactivationRequest(
                    licenseKey = licenseKey,
                    instanceId = instanceId,
                )
            )
        }
    }
}