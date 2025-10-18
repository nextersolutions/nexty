package com.nextersolutions.nexty

import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun get_or_default_is_correct() {
        val result = Nexty.getOrDefault("key", null)
        Assert.assertNull(result)
    }

    @Test
    fun key_remove_is_correct() {
        val key = "key"
        val value = "value"
        Nexty.put(key, value)

        val old = Nexty.remove<String>(key)
        assertEquals(value, old)
    }

    @Test
    fun key_delete_is_correct() {
        val key = "key"
        val value = "value"
        Nexty.put(key, value)
        Nexty.delete(key)
        val old = Nexty.get<String>(key)
        assertEquals(null, old)
    }
}
