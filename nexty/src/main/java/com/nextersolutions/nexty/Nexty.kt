package com.nextersolutions.nexty

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

object Nexty {
    private val lock = Object()
    private val pairs = mutableMapOf<String, Any?>()
        get() = synchronized(lock) { field }
    
    private val mutablePairs = mutableMapOf<String, MutableStateFlow<Any?>>()
        get() = synchronized(lock) { field }

    fun put(key: String, value: Any?) {
        pairs[key] = value
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String): T? {
        val value = pairs.getOrDefault(key, null)
        return try {
            value as? T
        } catch (_: Exception) {
            null
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun putMutable(key: String, value: Any?): Flow<Any?> {
        var old = mutablePairs.getOrDefault(key, null)
        if (old == null) {
            old = MutableStateFlow(value)
            mutablePairs[key] = old
        } else {
            old.value = value
        }

        return old.asSharedFlow()
    }

    fun getAsFlow(key: String): Flow<Any?>? {
        return mutablePairs.getOrDefault(key, null)?.asSharedFlow()
    }
}