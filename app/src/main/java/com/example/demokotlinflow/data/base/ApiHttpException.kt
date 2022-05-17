package com.example.demokotlinflow.data.base

import java.io.IOException


open class ApiHttpException(private val response: ErrorResponse) : IOException() {

    companion object {
        const val CODE_BAD_REQUEST = 400
        const val CODE_UNAUTHORIZED = 401
        const val CODE_AUTHORIZED_TIMEOUT = 419
        const val CODE_FORBIDDEN = 403
        const val CODE_NOT_FOUND = 404
        const val CODE_UNPROCESS_ABLE = 422
        const val CODE_SERVER_ERROR = 500
    }

    class BadRequestExceptionApi(response: ErrorResponse) : ApiHttpException(response)
    class NotFoundExceptionApi(response: ErrorResponse) : ApiHttpException(response)
    class ForbiddenExceptionApi(response: ErrorResponse) : ApiHttpException(response)
    class NetWorkExceptionApi(response: ErrorResponse) : ApiHttpException(response)
    class ServerExceptionApi(response: ErrorResponse) : ApiHttpException(response)
    class UnauthorizedExceptionApi(response: ErrorResponse) : ApiHttpException(response)
    class UnprocessedAbleExceptionApi(response: ErrorResponse) : ApiHttpException(response)

    fun getErrorMessage(): String {
        return if (response.code > 0) {
            response.errors.orEmpty()
        } else {
            NetworkErrorInterceptor.UNKNOWN_ERROR
        }
    }

    fun isUnauthorizedException(): Boolean {
        return this is UnauthorizedExceptionApi
    }
}