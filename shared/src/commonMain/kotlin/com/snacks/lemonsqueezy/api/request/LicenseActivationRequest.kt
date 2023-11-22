package com.snacks.lemonsqueezy.api.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LicenseActivationRequest(
    @SerialName("license_key")
    val licenseKey: String,
    @SerialName("instance_name")
    val instanceName: String,
)
