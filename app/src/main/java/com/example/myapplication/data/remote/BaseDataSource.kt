package  com.example.myapplication.data.remote

import retrofit2.Response

/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource(service: Api) {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body != "") {
                    if (body is ApiResponse) {
                        if (body.status == "success") {
                            return Result.success(body)
                        } else {
                            if (body.message != null) {
                                return Result.error(body.message!!)
                            }
                        }
                    } else {
                        return Result.success(body)
                    }
                }
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        return Result.error("Network call has failed for a following reason: $message")
    }

}

