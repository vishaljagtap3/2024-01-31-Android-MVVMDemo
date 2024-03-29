package `in`.bitcode.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.bitcode.mvvmdemo.adapters.UsersAdapter
import `in`.bitcode.mvvmdemo.databinding.ActivityMainBinding
import `in`.bitcode.mvvmdemo.factory.MyViewModelFactory
import `in`.bitcode.mvvmdemo.fragments.UserDetailsFragment
import `in`.bitcode.mvvmdemo.models.UserModel
import `in`.bitcode.mvvmdemo.network.UsersService
import `in`.bitcode.mvvmdemo.repo.UsersRepo
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
        initListeners()

        usersViewModel.fetchUsers()
    }

    private fun initListeners() {
        usersAdapter.onUserClickListener =
            object : UsersAdapter.OnUserClickListener {
                override fun onUserClick(user: UserModel, position: Int) {
                    //Add user details fragment
                    val userDetailsFragment = UserDetailsFragment()

                    val input = Bundle()
                    input.putInt("id", user.id)
                    userDetailsFragment.arguments = input

                    supportFragmentManager.beginTransaction()
                        .add(R.id.mainContainer, userDetailsFragment, "userdetailsfragment")
                        .addToBackStack(null)
                        .commit()
                }
            }
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

        usersViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
                UsersRepo(
                    UsersService.getInstance()
                )
            )
        )
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