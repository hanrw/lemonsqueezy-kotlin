package com.snacks.lemonsqueezy.api.request

import kotlinx.serialization.SerialName

data class LicenseDeactivationRequest(
    @SerialName("license_key")
    val licenseKey: String,
    @SerialName("instance_id")
    val instanceId: String,
)
