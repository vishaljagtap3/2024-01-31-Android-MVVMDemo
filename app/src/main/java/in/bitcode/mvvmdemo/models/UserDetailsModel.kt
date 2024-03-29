package `in`.bitcode.mvvmdemo.models

import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(
    @SerializedName("data")
    val userDetails : UserDetailsModel
)

data class UserDetailsModel(
    val id : Int,
    val email : String,
    @SerializedName("first_name")
    val firstName : String,
    @SerializedName("last_name")
    val lastName : String,
    val avatar : String
)