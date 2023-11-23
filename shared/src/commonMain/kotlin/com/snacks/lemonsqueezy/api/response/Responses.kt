package com.snacks.lemonsqueezy.api.response

import com.snacks.lemonsqueezy.api.data.Instance
import com.snacks.lemonsqueezy.api.data.LicenseKey
import com.snacks.lemonsqueezy.api.data.Meta
import com.snacks.lemonsqueezy.api.data.UserAttributes
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


/**
 * {
 *   "type": "users",
 *   "id": "1",
 *   "attributes": {
 *     "name": "Darlene Daugherty",
 *     "email": "gernser@yahoo.com",
 *     "color": "#898FA9",
 *     "avatar_url": "https://www.gravatar.com/avatar/1ace5b3965c59dbcd1db79d85da75048?d=blank",
 *     "has_custom_avatar": false,
 *     "createdAt": "2021-05-24T14:08:31.000000Z",
 *     "updatedAt": "2021-08-26T13:24:54.000000Z"
 *   }
 * }
 */
data class UserResponse(
    val type: String,
    val id: String,
    val attributes: UserAttributes,
)