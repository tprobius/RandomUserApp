package com.tprobius.randomuserapp.presentation.userdetails

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.tprobius.randomuserapp.R
import com.tprobius.randomuserapp.databinding.FragmentUserDetailsBinding
import com.tprobius.randomuserapp.domain.entities.RandomUser
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding isn't initialized" }

    private val viewModel: UserDetailsViewModel by viewModel()

    private lateinit var user: RandomUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.user as RandomUser
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserDetails(user)
        viewModel.state.observe(viewLifecycleOwner, ::handleState)

        setOnPhoneNumberClick()
        setOnEmailClick()
        setOnLocationClick()
        setOnTryAgainClick()
    }

    private fun handleState(state: UserDetailsState) {
        when (state) {
            UserDetailsState.Initial -> showInitialState()
            UserDetailsState.Loading -> showLoadingState()
            is UserDetailsState.Success -> showSuccessState()
            UserDetailsState.Error -> showErrorState()
        }
    }

    private fun showInitialState() {
        with(binding) {
            progressBar.isVisible = false
            errorImageView.isVisible = false
            errorTextView.isVisible = false
            tryAgainButton.isVisible = false

            with(userDetailsLayout) {
                photoImageView.isVisible = false
                userNameTextView.isVisible = false
                dividerView.isVisible = false
                phoneTextView.isVisible = false
                phoneValueTextView.isVisible = false
                emailTextView.isVisible = false
                emailValueTextView.isVisible = false
                addressTextView.isVisible = false
                addressValueTextView.isVisible = false
                timezoneTextView.isVisible = false
                timezoneValueTextView.isVisible = false
                dobTextView.isVisible = false
                dobValueTextView.isVisible = false
            }
        }
    }

    private fun showLoadingState() {
        with(binding) {
            progressBar.isVisible = true
            errorImageView.isVisible = false
            errorTextView.isVisible = false
            tryAgainButton.isVisible = false

            with(userDetailsLayout) {
                photoImageView.isVisible = false
                userNameTextView.isVisible = false
                dividerView.isVisible = false
                phoneTextView.isVisible = false
                phoneValueTextView.isVisible = false
                emailTextView.isVisible = false
                emailValueTextView.isVisible = false
                addressTextView.isVisible = false
                addressValueTextView.isVisible = false
                timezoneTextView.isVisible = false
                timezoneValueTextView.isVisible = false
                dobTextView.isVisible = false
                dobValueTextView.isVisible = false
            }
        }
    }

    private fun showSuccessState() {
        with(binding) {
            progressBar.isVisible = false
            errorImageView.isVisible = false
            errorTextView.isVisible = false
            tryAgainButton.isVisible = false

            with(userDetailsLayout) {
                photoImageView.isVisible = true
                userNameTextView.isVisible = true
                dividerView.isVisible = true
                phoneTextView.isVisible = true
                phoneValueTextView.isVisible = true
                emailTextView.isVisible = true
                emailValueTextView.isVisible = true
                addressTextView.isVisible = true
                addressValueTextView.isVisible = true
                timezoneTextView.isVisible = true
                timezoneValueTextView.isVisible = true
                dobTextView.isVisible = true
                dobValueTextView.isVisible = true
            }

            with(userDetailsLayout) {
                Glide.with(photoImageView)
                    .load(user.pictureDmn?.large)
                    .placeholder(R.drawable.ic_image_placeholder)
                    .transform(RoundedCorners(500))
                    .into(photoImageView)
                userNameTextView.text = "${user.nameDmn?.first} ${user.nameDmn?.last}"
                phoneValueTextView.text = user.phone
                emailValueTextView.text = user.email
                addressValueTextView.text =
                    "${user.locationDmn?.country}, ${user.locationDmn?.city}, ${user.locationDmn?.streetDmn?.name}, ${user.locationDmn?.streetDmn?.number}"
                timezoneValueTextView.text = user.locationDmn?.timezoneDmn?.description
                dobValueTextView.text = user.dobDmn?.date
            }
        }
    }

    private fun showErrorState() {
        with(binding) {
            progressBar.isVisible = false
            errorImageView.isVisible = true
            errorTextView.isVisible = true
            tryAgainButton.isVisible = true

            with(userDetailsLayout) {
                photoImageView.isVisible = false
                userNameTextView.isVisible = false
                dividerView.isVisible = false
                phoneTextView.isVisible = false
                phoneValueTextView.isVisible = false
                emailTextView.isVisible = false
                emailValueTextView.isVisible = false
                addressTextView.isVisible = false
                addressValueTextView.isVisible = false
                timezoneTextView.isVisible = false
                timezoneValueTextView.isVisible = false
                dobTextView.isVisible = false
                dobValueTextView.isVisible = false
            }
        }
    }

    private fun setOnPhoneNumberClick() {

    }

    private fun setOnEmailClick() {

    }

    private fun setOnLocationClick() {

    }

    private fun setOnTryAgainClick() {

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val USER = "user"

        private var Bundle.user
            get() =
                when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getParcelable(
                        USER,
                        RandomUser::class.java
                    )

                    else -> @Suppress("DEPRECATION") getParcelable(USER) as? RandomUser
                }
            set(value) = putParcelable(USER, value)

        fun newInstance(user: RandomUser) = UserDetailsFragment().apply {
            arguments = Bundle().apply { this.user = user }
        }
    }
}