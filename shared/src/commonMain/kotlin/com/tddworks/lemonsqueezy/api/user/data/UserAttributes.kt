package com.tddworks.lemonsqueezy.api.user.data

import kotlinx.serialization.SerialName

data class UserAttributes(
    val name: String,
    val email: String,
    val color: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("has_custom_avatar")
    val hasCustomAvatar: Boolean,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
)