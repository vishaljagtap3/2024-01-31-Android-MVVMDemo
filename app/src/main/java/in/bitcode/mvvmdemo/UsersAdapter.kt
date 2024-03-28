package `in`.bitcode.mvvmdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import `in`.bitcode.mvvmdemo.databinding.UserViewBinding
import `in`.bitcode.mvvmdemo.models.UserModel

class UsersAdapter(
    private val users : ArrayList<UserModel>
) : Adapter<UsersAdapter.UserViewHolder>(){

    class UserViewHolder(view : View) : ViewHolder(view) {
        val userViewBinding : UserViewBinding

        init {
            userViewBinding = UserViewBinding.bind(view)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_view, null)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.userViewBinding.user = users[position]
    }
}
