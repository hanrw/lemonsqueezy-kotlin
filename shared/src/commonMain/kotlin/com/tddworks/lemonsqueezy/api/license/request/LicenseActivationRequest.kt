package com.tddworks.lemonsqueezy.api.license.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LicenseActivationRequest(
    @SerialName("license_key")
    val licenseKey: String,
    @SerialName("instance_name")
    val instanceName: String,
)
