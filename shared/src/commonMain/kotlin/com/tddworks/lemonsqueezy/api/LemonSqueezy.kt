package com.tddworks.lemonsqueezy.api

import com.tddworks.lemonsqueezy.api.LemonSqueezy.Companion.BASE_URL
import com.tddworks.lemonsqueezy.api.internal.network.ktor.HttpRequester
import com.tddworks.lemonsqueezy.api.internal.network.ktor.internal.createHttpClient
import com.tddworks.lemonsqueezy.api.internal.network.ktor.default
import com.tddworks.lemonsqueezy.api.license.LemonSqueezyLicenseKeysApi
import com.tddworks.lemonsqueezy.api.license.LicenseKeysApi
import com.tddworks.lemonsqueezy.api.user.LemonSqueezyUsersApi
import com.tddworks.lemonsqueezy.api.user.UsersApi
import getPlatform

interface LemonSqueezy : LicenseKeysApi, UsersApi {
    companion object {
        const val BASE_URL = "api.lemonsqueezy.com"
    }
}

fun LemonSqueezy(token: String): LemonSqueezy = LemonSqueezyApi(
    HttpRequester.default(
        createHttpClient(
            url = BASE_URL,
            token = token,
            engine = getPlatform().engine
        )
    ),
)

class LemonSqueezyApi(private val requester: HttpRequester) : LemonSqueezy,
    UsersApi by LemonSqueezyUsersApi(
        requester
    ),
    LicenseKeysApi by LemonSqueezyLicenseKeysApi(
        requester
    )