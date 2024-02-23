package com.tprobius.randomuserapp.presentation.userslist.userslistadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.tprobius.randomuserapp.databinding.ItemUserBinding
import com.tprobius.randomuserapp.domain.entities.RandomUser

class UsersListAdapter(
    private val onClickListener: (RandomUser) -> Unit,
    private val onPhoneNumberClick: (RandomUser) -> Unit,
    private val onEmailClick: (RandomUser) -> Unit,
    private val onLocationClick: (RandomUser) -> Unit
) : ListAdapter<RandomUser, UserViewHolder>(UsersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context)),
            onClickListener,
            onPhoneNumberClick,
            onEmailClick,
            onLocationClick
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(getItem(position))
}