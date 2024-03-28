package `in`.bitcode.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.bitcode.mvvmdemo.databinding.ActivityMainBinding
import `in`.bitcode.mvvmdemo.models.UserModel
import `in`.bitcode.mvvmdemo.viewmodels.UsersViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initViewModels()
        initAdapter()
        initObservers()

        usersViewModel.fetchUsers()
    }

    private fun initObservers() {

        usersViewModel.usersLiveData.observe(
            this
        ) {
            if(it) {
                usersAdapter.notifyDataSetChanged()
            }
            else {
                Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        }

        /*usersViewModel.usersLiveData.observe(
            this,
            object : Observer<Boolean> {
                override fun onChanged(value: Boolean) {
                    Toast.makeText(this@MainActivity, "Data changed", Toast.LENGTH_LONG).show()
                    usersAdapter.notifyDataSetChanged()
                }
            }
        )*/
    }

    private fun initViewModels() {
        //this viewmodel object is not attached to activity
        //usersViewModel = UsersViewModel()

        usersViewModel = ViewModelProvider(this)
            .get(UsersViewModel::class.java)
    }

    private fun initAdapter() {
        usersAdapter = UsersAdapter( usersViewModel.users );
        activityMainBinding.recyclerUsers.adapter = usersAdapter
    }

    private fun initViews() {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.recyclerUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}