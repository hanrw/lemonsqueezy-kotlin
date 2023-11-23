package com.snacks.lemonsqueezy.api.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
)

@Serializable
data class Instance(
    val id: String,
    val name: String,
    @SerialName("created_at")
    val createdAt: String,
)

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
)

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