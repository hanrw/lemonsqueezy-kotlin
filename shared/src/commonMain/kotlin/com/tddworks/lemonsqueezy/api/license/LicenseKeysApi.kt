package com.tddworks.lemonsqueezy.api.license

import com.tddworks.lemonsqueezy.api.internal.network.ktor.HttpRequester
import com.tddworks.lemonsqueezy.api.internal.network.ktor.performRequest
import com.tddworks.lemonsqueezy.api.license.request.LicenseActivationRequest
import com.tddworks.lemonsqueezy.api.license.request.LicenseDeactivationRequest
import com.tddworks.lemonsqueezy.api.license.response.LicenseActivationErrorResponse
import com.tddworks.lemonsqueezy.api.license.response.LicenseActivationResult
import com.tddworks.lemonsqueezy.api.license.response.LicenseActivationSuccessResponse
import com.tddworks.lemonsqueezy.api.license.response.LicenseDeactivationResponse
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
        val activationRequest = createLicenseActivationRequest(licenseKey, instanceName)
        val activationResult = performLicenseActivationRequest(activationRequest)
        return handleActivationResponse(activationResult)
    }

    private fun createLicenseActivationRequest(licenseKey: String, instanceName: String): LicenseActivationRequest {
        return LicenseActivationRequest(
            licenseKey = licenseKey,
            instanceName = instanceName
        )
    }

    private suspend fun performLicenseActivationRequest(request: LicenseActivationRequest): String {
        val activationResult = requester.performRequest<String> {
            method = HttpMethod.Post
            url(path = "/v1/licenses/activate")
            setBody(request)
        }
        return activationResult
    }

    private fun handleActivationResponse(activationResult: String): LicenseActivationResult {
        return try {
            json.decodeFromString<LicenseActivationSuccessResponse>(activationResult)
        } catch (ex: Exception) {
            json.decodeFromString<LicenseActivationErrorResponse>(activationResult)
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