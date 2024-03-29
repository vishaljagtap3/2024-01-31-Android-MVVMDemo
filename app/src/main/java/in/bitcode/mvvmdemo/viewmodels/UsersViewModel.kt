package `in`.bitcode.mvvmdemo.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import `in`.bitcode.mvvmdemo.models.UserModel
import `in`.bitcode.mvvmdemo.network.UsersService
import `in`.bitcode.mvvmdemo.repo.UsersRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class UsersViewModel(
    private val usersRepo: UsersRepo
) : ViewModel() {

    val users = ArrayList<UserModel>()
    val usersLiveData = MutableLiveData<Boolean>()

    fun fetchUsers() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                //val usersResponse = usersService.fetchUsers() //old one
                val usersResponse = usersRepo.fetchUsers()

                withContext(Dispatchers.Main) {
                    if(usersResponse != null) {
                        users.addAll(usersResponse.users)
                        usersLiveData.postValue(true)
                    }
                    else {
                        usersLiveData.postValue(false)
                    }
                }
            }
            catch (e : Exception) {
                usersLiveData.postValue(false)
            }
        }

    }
}