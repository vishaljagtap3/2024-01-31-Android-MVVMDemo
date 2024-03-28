package `in`.bitcode.mvvmdemo.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import `in`.bitcode.mvvmdemo.models.UserModel
import `in`.bitcode.mvvmdemo.network.UsersService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class UsersViewModel : ViewModel() {

    val users = ArrayList<UserModel>()
    val usersLiveData = MutableLiveData<Boolean>()

    private var usersService : UsersService = UsersService.getInstance()

    fun fetchUsers() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val usersResponse = usersService.fetchUsers()

                Log.e("tag", "Got ${usersResponse.users.size} no. of users!")

                withContext(Dispatchers.Main) {
                    users.addAll(usersResponse.users)
                    usersLiveData.postValue(true)
                }
            }
            catch (e : Exception) {
                usersLiveData.postValue(false)
            }
        }

    }
}