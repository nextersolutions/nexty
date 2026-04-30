package com.nextersolutions.nexty

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

object Nexty {
    private val lock = Any()
    private val pairs = mutableMapOf<String, Any?>()
        get() = synchronized(lock) { field }

    private val mutablePairs = mutableMapOf<String, MutableStateFlow<Any?>>()
        get() = synchronized(lock) { field }

    fun put(key: String, value: Any?) {
        pairs[key] = value
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(key: String): T? {
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

    suspend inline fun <reified T> getOrElse(
        key: String,
        crossinline ifNull: suspend () -> T?
    ): T? {
        val value = this.get<T>(key)

        return when (value != null) {
            true -> {
                put(key, value)
                value
            }

            false -> ifNull.invoke()
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
     * Allows to put with 2 keys
     */
    fun put(key1: String, key2: String, value: Any?) {
        val key = key1 + "_" + key2
        pairs[key] = value
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key1: String, key2: String): T? {
        val key = key1 + "_" + key2
        val value = pairs.getOrDefault(key, null)
        return try {
            value as? T
        } catch (_: Exception) {
            null
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getOrDefault(key1: String, key2: String, default: T): T {
        val key = key1 + "_" + key2
        val value = pairs.getOrDefault(key, null)
        return try {
            (value as? T) ?: default
        } catch (_: Exception) {
            default
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> remove(key1: String, key2: String): T? {
        val key = key1 + "_" + key2
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
     * @return flow for the key, if exists. Otherwise, returns null
     */
    fun getAsFlow(key: String): Flow<Any?>? {
        return mutablePairs.getOrDefault(key, null)?.asSharedFlow()
    }

    /**
     * Clears everything except mutable pairs
     */
    fun clear() {
        pairs.clear()
    }

    /**
     * Clear mutable pairs only
     */
    fun clearMutable() {
        mutablePairs.clear()
    }

    fun clearAll() {
        pairs.clear()
        mutablePairs.clear()
    }
}