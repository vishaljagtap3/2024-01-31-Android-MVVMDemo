package `in`.bitcode.mvvmdemo.repo

import `in`.bitcode.mvvmdemo.models.UserDetailsResponse
import `in`.bitcode.mvvmdemo.network.UsersService
import java.lang.Exception

class UserDetailsRepo(
    private val usersService: UsersService
) : BaseRepo() {

    suspend fun fetchUserDetails(id : Int) : UserDetailsResponse? {
        try {
            return usersService.fetchUserDetails(id)
        }
        catch (e: Exception){}
        return null
    }
}