package com.exercise.firstdigitalbank.tmdb.network

import com.exercise.firstdigitalbank.tmdb.R
import com.exercise.firstdigitalbank.tmdb.util.ResourcesLoader
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OkHttpInterceptor @Inject constructor(private val resLoader: ResourcesLoader) : Interceptor {

    companion object RequestParams {
        const val API_KEY = "api_key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .prepareUrl(request)
            //.addHeaders(request)
            .build()

        return chain.proceed(newRequest)
    }

    private fun Request.Builder.prepareUrl(request: Request): Request.Builder {
            val newUrl = request.url.newBuilder()
                .addQueryParameter(API_KEY, resLoader.getString(R.string.api_key_v3))
                .build()

        return url(newUrl)
    }
}

private fun Headers.contains(headerKey: String): Boolean = !this.get(headerKey).isNullOrEmpty()

