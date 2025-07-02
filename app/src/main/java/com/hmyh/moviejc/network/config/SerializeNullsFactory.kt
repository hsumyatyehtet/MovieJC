package com.hmyh.moviejc.network.config

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

/**
 * Created by H.M.Y.H on 2/07/2025.
 */

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class SerializeNulls

class SerializeNullsFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: Set<Annotation?>, moshi: Moshi): JsonAdapter<*>? {
        val nextAnnotations = Types.nextAnnotations(
            annotations,
            SerializeNulls::class.java
        ) ?: return null
        return moshi.nextAdapter<Any>(this, type, nextAnnotations).serializeNulls()
    }
}