package com.snacks.lemonsqueezy.api

import com.snacks.lemonsqueezy.api.LemonSqueezy.Companion.BASE_URL
import com.snacks.lemonsqueezy.api.internal.network.ktor.HttpRequester
import com.snacks.lemonsqueezy.api.internal.network.ktor.internal.createHttpClient
import com.snacks.lemonsqueezy.api.internal.network.ktor.default
import com.snacks.lemonsqueezy.api.license.LemonSqueezyLicenseKeysApi
import com.snacks.lemonsqueezy.api.license.LicenseKeysApi
import com.snacks.lemonsqueezy.api.user.LemonSqueezyUsersApi
import com.snacks.lemonsqueezy.api.user.UsersApi
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