package com.snacks.lemonsqueezy.api.user

import com.snacks.lemonsqueezy.api.internal.network.ktor.HttpRequester
import com.snacks.lemonsqueezy.api.internal.network.ktor.performRequest
import com.snacks.lemonsqueezy.api.user.response.UserResponse
import io.ktor.client.request.*
import io.ktor.http.*

/**
 * A user represents your personal user account that you use to log in to Lemon Squeezy.
 */
interface UsersApi {
    suspend fun me(): UserResponse
}

class LemonSqueezyUsersApi(
    private val requester: HttpRequester,
) : UsersApi {
    override suspend fun me(): UserResponse {
        return requester.performRequest<UserResponse> {
            method = HttpMethod.Get
            contentType(ContentType("application", "vnd.api+json"))
            url(path = "/v1/users/me")
        }
    }
}