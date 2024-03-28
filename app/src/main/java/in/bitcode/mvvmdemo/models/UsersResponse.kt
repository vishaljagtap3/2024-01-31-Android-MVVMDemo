package `in`.bitcode.mvvmdemo.models

import com.google.gson.annotations.SerializedName

class UsersResponse(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("data")
    val users : ArrayList<UserModel>

)