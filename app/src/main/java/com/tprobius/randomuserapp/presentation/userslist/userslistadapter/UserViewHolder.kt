package com.tprobius.randomuserapp.presentation.userslist.userslistadapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.tprobius.randomuserapp.R
import com.tprobius.randomuserapp.databinding.ItemUserBinding
import com.tprobius.randomuserapp.domain.entities.RandomUser

class UserViewHolder(
    private var binding: ItemUserBinding,
    private val onClickListener: (RandomUser) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: RandomUser) {
        binding.userNameTextView.text = "${user.first} ${user.last}"
        binding.phoneNumberTextView.text = user.phone
        binding.emailAddressTextView.text = user.email
        binding.locationTextView.text =
            "${user.country}, ${user.city}, ${user.street}, ${user.number}"

        Glide.with(binding.imageView)
            .load(user.medium)
            .placeholder(R.drawable.ic_image_placeholder)
            .transform(RoundedCorners(150))
            .into(binding.imageView)

        itemView.setOnClickListener {
            onClickListener(user)
        }
    }
}