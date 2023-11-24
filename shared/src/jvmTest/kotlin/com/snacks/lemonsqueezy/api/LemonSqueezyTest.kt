package com.snacks.lemonsqueezy.api

import org.junit.Assert.assertEquals
import org.junit.Test

class LemonSqueezyTest {

    @Test
    fun `should return correct base url`() {
        assertEquals("api.lemonsqueezy.com", LemonSqueezy.BASE_URL)
    }
}