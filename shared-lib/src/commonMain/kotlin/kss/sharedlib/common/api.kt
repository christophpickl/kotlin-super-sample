package kss.sharedlib.common

interface ServerApi {
    fun fetchUsers(): List<UserRto>
}

class ApiException(message: String, cause: Throwable? = null) : Exception(message, cause)
