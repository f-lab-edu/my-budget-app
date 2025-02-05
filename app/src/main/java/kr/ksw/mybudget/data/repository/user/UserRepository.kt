package kr.ksw.mybudget.data.repository.user

interface UserRepository {
    suspend fun setUserName(userName: String)

    suspend fun getUserName(): Result<String>
}