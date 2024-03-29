package `in`.bitcode.mvvmdemo.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import `in`.bitcode.mvvmdemo.models.UserDetailsModel
import `in`.bitcode.mvvmdemo.repo.UserDetailsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDetailsViewModel(
    private val userDetailsRepo: UserDetailsRepo
) : ViewModel() {

    val userDetailsLiveData = MutableLiveData<UserDetailsModel?>()

    fun fetchUserDetails(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val userDetailsResponse = userDetailsRepo.fetchUserDetails(id)

            Log.e("tag", userDetailsResponse.toString())

            withContext(Dispatchers.Main) {
                if (userDetailsResponse != null) {
                    userDetailsLiveData.postValue(userDetailsResponse.userDetails)
                } else {
                    userDetailsLiveData.postValue(null)
                }
            }
        }
    }
}