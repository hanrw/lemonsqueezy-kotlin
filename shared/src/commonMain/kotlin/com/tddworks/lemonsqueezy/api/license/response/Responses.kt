package com.tddworks.lemonsqueezy.api.license.response

import com.tddworks.lemonsqueezy.api.license.data.Instance
import com.tddworks.lemonsqueezy.api.license.data.LicenseKey
import com.tddworks.lemonsqueezy.api.license.data.Meta
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

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

data class LicenseDeactivationResponse(
    val deactivated: Boolean,
    val error: String?,
    val licenseKey: LicenseKey,
    val meta: Meta,
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