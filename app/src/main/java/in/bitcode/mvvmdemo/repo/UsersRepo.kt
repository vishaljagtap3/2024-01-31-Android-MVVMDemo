package `in`.bitcode.mvvmdemo.repo

import `in`.bitcode.mvvmdemo.models.UsersResponse
import `in`.bitcode.mvvmdemo.network.UsersService

class UsersRepo(
    private val usersService: UsersService
) : BaseRepo() {

    suspend fun fetchUsers(): UsersResponse? {
        return usersService.fetchUsers()

    }
}