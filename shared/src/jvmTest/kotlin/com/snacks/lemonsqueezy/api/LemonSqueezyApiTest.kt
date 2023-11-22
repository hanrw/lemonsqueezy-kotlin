package com.snacks.lemonsqueezy.api

import org.junit.Assert.assertEquals
import org.junit.Test

class LemonSqueezyApiTest {

    @Test
    fun `should return correct base url`() {
        assertEquals("api.lemonsqueezy.com", LemonSqueezyApi.BASE_URL)
    }
}