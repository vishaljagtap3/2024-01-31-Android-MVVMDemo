package `in`.bitcode.mvvmdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import `in`.bitcode.mvvmdemo.R
import `in`.bitcode.mvvmdemo.databinding.UserViewBinding
import `in`.bitcode.mvvmdemo.models.UserModel

class UsersAdapter(
    private val users : ArrayList<UserModel>
) : Adapter<UsersAdapter.UserViewHolder>(){

    interface OnUserClickListener {
        fun onUserClick(user : UserModel, position: Int)
    }
    var onUserClickListener : OnUserClickListener? = null

    inner class UserViewHolder(view : View) : ViewHolder(view) {
        val userViewBinding : UserViewBinding
        init {
            userViewBinding = UserViewBinding.bind(view)

            userViewBinding.root.setOnClickListener {
                onUserClickListener?.onUserClick(users[adapterPosition], adapterPosition)
            }
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
