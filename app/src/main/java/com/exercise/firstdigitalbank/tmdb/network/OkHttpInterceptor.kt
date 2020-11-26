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

    companion object RequestHeaders {
        const val NO_AUTH_HEADER_KEY = "No-Authentication"
        const val DEFAULT_BASE_URL_KEY = "Static-Base-Url"
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

    /*private fun Response.extractHeaders(sessionManager: SessionManager) {
        when {
            headers().contains(AUTHORIZATION_TOKEN_KEY) -> {
                sessionManager.setAuthorizationToken(header(AUTHORIZATION_TOKEN_KEY)!!)
            }
            headers().contains(AUTHENTICATION_TOKEN_KEY) -> {
                sessionManager.setAuthorizationToken(header(AUTHENTICATION_TOKEN_KEY)!!)
            }
        }
    }

    private fun Request.Builder.addHeaders(request: Request): Request.Builder {
        if (!request.headers().contains(NO_AUTH_HEADER_KEY)) {
            *//* Apollo GraphQL Headers *//*
            addHeader(APOLLO_CLIENT_NAME_KEY, BuildConfig.APPLICATION_ID)
            addHeader(APOLLO_CLIENT_VERSION_KEY, BuildConfig.VERSION_NAME)
            *//* Auth's Tokens Headers *//*
            addHeader(AUTHENTICATION_TOKEN_KEY, sessionManager.authToken)
            addHeader(REFRESH_TOKEN_KEY, sessionManager.refreshToken)
            val authorizationToken = sessionManager.authorizationToken
            if (authorizationToken.isNotEmpty()) {
                addHeader(AUTHORIZATION_TOKEN_KEY, sessionManager.authorizationToken)
            }

            val projectName = userRepo.getSelectedProjectName()
            *//* Project Name Header *//*
            if (projectName.isNotEmpty()) {
                addHeader(PROJECT_NAME_KEY, projectName)
            }
        }

        return this
    }*/
}

private fun Headers.contains(headerKey: String): Boolean = !this.get(headerKey).isNullOrEmpty()

