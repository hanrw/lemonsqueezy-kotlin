package com.snacks.lemonsqueezy.api

import com.snacks.lemonsqueezy.api.internal.ktor.HttpRequester
import com.snacks.lemonsqueezy.api.internal.ktor.performRequest
import com.snacks.lemonsqueezy.api.request.LicenseActivationRequest
import com.snacks.lemonsqueezy.api.request.LicenseDeactivationRequest
import com.snacks.lemonsqueezy.api.response.LicenseActivationErrorResponse
import com.snacks.lemonsqueezy.api.response.LicenseActivationResult
import com.snacks.lemonsqueezy.api.response.LicenseActivationSuccessResponse
import com.snacks.lemonsqueezy.api.response.LicenseDeactivationResponse
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

interface LicenseApi {
    suspend fun activeLicense(licenseKey: String, instanceName: String): LicenseActivationResult

    suspend fun deactivateLicense(licenseKey: String, instanceId: String): LicenseDeactivationResponse
}

internal class LemonSqueezyLicenseApi(
    private val requester: HttpRequester,
) : LicenseApi {
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun activeLicense(licenseKey: String, instanceName: String): LicenseActivationResult {
        try {
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
        } catch (e: Exception) {
            throw e
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