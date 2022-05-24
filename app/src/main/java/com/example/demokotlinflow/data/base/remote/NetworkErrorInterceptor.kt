package com.example.demokotlinflow.data.base.remote

import android.accounts.NetworkErrorException
import android.text.TextUtils
import com.example.demokotlinflow.util.isNetworkAvailable
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkErrorInterceptor @Inject constructor(
    private val noNetworkMessage: String,
    private val gson: Gson,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable()) {
            val errorBody = ErrorResponse()
            errorBody.code = 300
            errorBody.errors = noNetworkMessage
            throw ApiHttpException.NetWorkExceptionApi(errorBody)
        }

        val request =
            chain.request().newBuilder()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo3NTYsInVzZXJzX3R5cGVfaWQiOjd9.8OglsM4eTqRVnxHQf5Qsue_Zo14d6ZZ3K0iyKQq7adA")
                .header("requestFrom", "mobile")
                .header("deviceId", "de55dac5bf9596ee")
                .header("roleId", "7")
//                .header("AppVersion", BuildConfig.VERSION_NAME)
//                .header("AppBuildNumber", BuildConfig.VERSION_CODE.toString())
                .build()

        val response = request.let { chain.proceed(it) }
            ?: throw NetworkErrorException("Null response")

        return if (response.isSuccessful) {
            response
        } else {
            //Handle error structure for http request
            var errorBody: ErrorResponse
            try {
                errorBody = gson.fromJson(response.body?.string(), ErrorResponse::class.java)
                errorBody.code = response.code
            } catch (exception: JsonSyntaxException) {
                errorBody = ErrorResponse()
                errorBody.code = response.code
                when {
                    (response.code == ApiHttpException.CODE_UNAUTHORIZED) ||
                            (response.code == ApiHttpException.CODE_AUTHORIZED_TIMEOUT) -> {
                        errorBody.statusType = UNAUTHORIZED
                    }
                    TextUtils.isEmpty(response.message) -> {
                        errorBody.statusType = UNKNOWN_ERROR
                    }
                    else -> {
                        errorBody.statusType = response.message
                    }
                }
            } catch (exception: Exception) {
                errorBody = ErrorResponse()
                errorBody.code = response.code
                when {
                    (response.code == ApiHttpException.CODE_UNAUTHORIZED) ||
                            (response.code == ApiHttpException.CODE_AUTHORIZED_TIMEOUT) -> {
                        errorBody.errors = UNAUTHORIZED
                    }
                    TextUtils.isEmpty(response.message) -> {
                        errorBody.errors = UNKNOWN_ERROR
                    }
                    else -> {
                        errorBody.errors = response.message
                    }
                }
            }
            throw createException(response.code, errorBody)
        }
    }

    private fun createException(code: Int, errorBody: ErrorResponse): ApiHttpException {
        return when (code) {
            ApiHttpException.CODE_BAD_REQUEST -> ApiHttpException.BadRequestExceptionApi(
                errorBody
            )
            ApiHttpException.CODE_NOT_FOUND -> ApiHttpException.NotFoundExceptionApi(
                errorBody
            )
            ApiHttpException.CODE_UNAUTHORIZED -> ApiHttpException.UnauthorizedExceptionApi(
                errorBody
            )
            ApiHttpException.CODE_AUTHORIZED_TIMEOUT -> ApiHttpException.UnauthorizedExceptionApi(
                errorBody
            )
            ApiHttpException.CODE_FORBIDDEN -> ApiHttpException.ForbiddenExceptionApi(
                errorBody
            )
            ApiHttpException.CODE_UNPROCESS_ABLE -> ApiHttpException.UnprocessedAbleExceptionApi(
                errorBody
            )
            ApiHttpException.CODE_SERVER_ERROR -> ApiHttpException.ServerExceptionApi(
                errorBody
            )
            else -> ApiHttpException(errorBody)
        }
    }

    companion object {
        const val UNKNOWN_ERROR = "UNKNOWN_ERROR"
        const val UNAUTHORIZED = "Unauthorized"
    }

}