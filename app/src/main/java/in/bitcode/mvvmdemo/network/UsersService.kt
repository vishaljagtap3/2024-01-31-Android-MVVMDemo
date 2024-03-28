package `in`.bitcode.mvvmdemo.network

import `in`.bitcode.mvvmdemo.models.UsersResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UsersService {

    @GET("api/users?page=2")
    suspend fun fetchUsers() : UsersResponse

    companion object {
        private var usersService : UsersService? = null

        fun getInstance() : UsersService {
            if(usersService == null) {
                usersService = Retrofit.Builder()
                    .baseUrl("https://reqres.in/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UsersService::class.java)
            }
            return usersService!!
        }
    }

}