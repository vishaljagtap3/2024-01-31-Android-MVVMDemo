package `in`.bitcode.mvvmdemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import `in`.bitcode.mvvmdemo.databinding.UserDetailsFragmentBinding
import `in`.bitcode.mvvmdemo.factory.MyViewModelFactory
import `in`.bitcode.mvvmdemo.network.UsersService
import `in`.bitcode.mvvmdemo.repo.UserDetailsRepo
import `in`.bitcode.mvvmdemo.viewmodels.UserDetailsViewModel


class UserDetailsFragment : Fragment() {

    private lateinit var userDetailsFragmentBinding: UserDetailsFragmentBinding
    private lateinit var userDetailsViewModel: UserDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userDetailsFragmentBinding = UserDetailsFragmentBinding.inflate(inflater)

        val id = requireArguments().getInt("id")
        Toast.makeText(requireContext(), "Got : $id", Toast.LENGTH_LONG).show()

        initViewModels()
        initObservers()

        userDetailsViewModel.fetchUserDetails(id)

        return userDetailsFragmentBinding.root
    }

    private fun initObservers() {
        userDetailsViewModel.userDetailsLiveData.observe(
            viewLifecycleOwner
        ) {
            if(it != null) {
                userDetailsFragmentBinding.userDetails = it
            }
            else {
                Toast.makeText(requireContext(), "Unable to fetch user details!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initViewModels() {
        userDetailsViewModel =
            ViewModelProvider(
                this,
                MyViewModelFactory(
                    UserDetailsRepo(
                        UsersService.getInstance()
                    )
                )
            ).get(UserDetailsViewModel::class.java)
    }

}