package com.tprobius.randomuserapp.presentation.userslist.userslistadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.tprobius.randomuserapp.databinding.ItemUserBinding
import com.tprobius.randomuserapp.domain.model.RandomUser

class UsersListAdapter(
    private val onClickListener: (RandomUser) -> Unit,
) : ListAdapter<RandomUser, UserViewHolder>(UsersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context)),
            onClickListener
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(getItem(position))
}