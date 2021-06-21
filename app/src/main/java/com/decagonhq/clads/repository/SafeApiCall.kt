package com.decagonhq.clads.repository

import com.decagonhq.clads.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            Resource.Loading
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> Resource.Error(true, null, null)
                    is HttpException -> {
                        val t = throwable.response()?.errorBody()?.charStream()
                        val code = throwable.code()
                        Resource.Error(
                            false,
                            code,
                            throwable.response() as Response<Any>
                        )
                    }
                    else -> {
                        Resource.Error(false, null, null)
                    }
                }
            }
        }
    }
}