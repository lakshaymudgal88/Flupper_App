package com.example.flupperapp.util

/**
 * This sealed class is generally used for Common Response we get from the servers/database so to
 * reduce the redundency of the code
 */
sealed class CommonResponse<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T?) : CommonResponse<T>(data)
    class Error<T>(data: T? = null, message: String) : CommonResponse<T>(data, message)
}
