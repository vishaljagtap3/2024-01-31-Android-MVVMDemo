package `in`.bitcode.mvvmdemo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import `in`.bitcode.mvvmdemo.network.UsersService
import `in`.bitcode.mvvmdemo.repo.BaseRepo
import `in`.bitcode.mvvmdemo.repo.UserDetailsRepo
import `in`.bitcode.mvvmdemo.repo.UsersRepo
import `in`.bitcode.mvvmdemo.viewmodels.UserDetailsViewModel
import `in`.bitcode.mvvmdemo.viewmodels.UsersViewModel
import java.security.InvalidParameterException

class MyViewModelFactory(
    private val repo : BaseRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(UsersViewModel::class.java) && repo is UsersRepo ) {
            return UsersViewModel( repo ) as T;
        }

        if(modelClass.isAssignableFrom(UserDetailsViewModel::class.java) && repo is UserDetailsRepo) {
            return UserDetailsViewModel(repo) as T
        }

        throw InvalidParameterException("No matching view model found")
    }

    /*override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(UsersRepo(UsersService.getInstance())) as T;
        }

        if(modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            return UserDetailsViewModel( UserDetailsRepo( UsersService.getInstance() ) ) as T
        }

        throw InvalidParameterException("No matching view model found")
    }*/
}