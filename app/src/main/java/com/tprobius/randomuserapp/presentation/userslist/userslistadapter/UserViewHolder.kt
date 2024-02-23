package com.tprobius.randomuserapp.presentation.userslist.userslistadapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.tprobius.randomuserapp.R
import com.tprobius.randomuserapp.databinding.ItemUserBinding
import com.tprobius.randomuserapp.domain.entities.RandomUser

class UserViewHolder(
    private var binding: ItemUserBinding,
    private val onClickListener: (RandomUser) -> Unit,
    private val onPhoneNumberClick: (RandomUser) -> Unit,
    private val onEmailClick: (RandomUser) -> Unit,
    private val onLocationClick: (RandomUser) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: RandomUser) {
        binding.userNameTextView.text = "${user.nameDmn?.first} ${user.nameDmn?.last}"
        binding.phoneNumberTextView.text = user.phone
        binding.emailAddressTextView.text = user.email
        binding.locationTextView.text =
            "${user.locationDmn?.country}, ${user.locationDmn?.city}, ${user.locationDmn?.streetDmn?.name}, ${user.locationDmn?.streetDmn?.number}"

        Glide.with(binding.imageView)
            .load(user.pictureDmn?.thumbnail)
            .placeholder(R.drawable.ic_image_placeholder)
            .transform(RoundedCorners(150))
            .into(binding.imageView)

        itemView.setOnClickListener {
            onClickListener(user)
        }

        binding.phoneNumberTextView.setOnClickListener {
            onPhoneNumberClick(user)
        }

        binding.emailAddressTextView.setOnClickListener {
            onEmailClick(user)
        }

        binding.locationTextView.setOnClickListener {
            onLocationClick(user)
        }
    }
}