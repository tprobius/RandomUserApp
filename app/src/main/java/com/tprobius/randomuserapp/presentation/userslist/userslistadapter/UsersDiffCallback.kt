package com.tprobius.randomuserapp.presentation.userslist.userslistadapter

import androidx.recyclerview.widget.DiffUtil
import com.tprobius.randomuserapp.domain.entities.RandomUser

class UsersDiffCallback : DiffUtil.ItemCallback<RandomUser>() {

    override fun areItemsTheSame(oldItem: RandomUser, newItem: RandomUser) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: RandomUser, newItem: RandomUser) =
        oldItem == newItem
}