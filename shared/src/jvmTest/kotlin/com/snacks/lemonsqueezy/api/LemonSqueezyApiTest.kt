package com.snacks.lemonsqueezy.api

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class LemonSqueezyApiTest {

    @Test
    fun `should return correct base url`() {
        assertEquals("api.lemonsqueezy.com", LemonSqueezyApi.BASE_URL)
    }
}