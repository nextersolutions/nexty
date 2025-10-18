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
    fun <T> getOrDefault(key: String, default: T): T {
        val value = pairs.getOrDefault(key, null)
        return try {
            (value as? T) ?: default
        } catch (_: Exception) {
            default
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> remove(key: String): T? {
        val value = pairs.remove(key)
        return try {
            value as? T
        } catch (_: Exception) {
            null
        }
    }

    /**
     * Silently deletes the key and value if exists
     */
    fun delete(key: String) {
        pairs.remove(key)
    }

    /**
     * Put mutable value: if flow for key exists, emits the value to the flow;
     * otherwise creates new flow with the value provided.
     *
     * @return flow with value
     */
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

    /**
     * @return flow for the key, if exists. Otherwise returns null
     */
    fun getAsFlow(key: String): Flow<Any?>? {
        return mutablePairs.getOrDefault(key, null)?.asSharedFlow()
    }
}