package com.snacks.lemonsqueezy.api

import com.snacks.lemonsqueezy.api.internal.ktor.HttpRequester
import com.snacks.lemonsqueezy.api.internal.ktor.internal.createHttpClient
import com.snacks.lemonsqueezy.api.internal.ktor.default
import getPlatform

class LemonSqueezyApi(token: String) : LicenseApi by LemonSqueezyLicenseApi(
    HttpRequester.default(
        createHttpClient(
            url = BASE_URL,
            token = token,
            engine = getPlatform().engine
        )
    )
) {
    companion object {
        const val BASE_URL = "api.lemonsqueezy.com"
    }
}