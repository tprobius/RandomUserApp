package com.tprobius.randomuserapp.presentation.userdetails

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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
        setOnBackClick()
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
        setViewsVisibility()
        setUserDataVisibility(false)
    }

    private fun setViewsVisibility(
        progressBarVisibility: Boolean = false,
        errorImageViewVisibility: Boolean = false,
        errorTextViewVisibility: Boolean = false,
        tryAgainButtonVisibility: Boolean = false
    ) {
        with(binding) {
            progressBar.isVisible = progressBarVisibility
            errorImageView.isVisible = errorImageViewVisibility
            errorTextView.isVisible = errorTextViewVisibility
            tryAgainButton.isVisible = tryAgainButtonVisibility
        }
    }

    private fun setUserDataVisibility(isVisible: Boolean) {
        with(binding.userDetailsLayout) {
            photoImageView.isVisible = isVisible
            userNameTextView.isVisible = isVisible
            dividerView.isVisible = isVisible
            phoneTextView.isVisible = isVisible
            phoneValueTextView.isVisible = isVisible
            emailTextView.isVisible = isVisible
            emailValueTextView.isVisible = isVisible
            addressTextView.isVisible = isVisible
            addressValueTextView.isVisible = isVisible
            timezoneTextView.isVisible = isVisible
            timezoneValueTextView.isVisible = isVisible
            dobTextView.isVisible = isVisible
            dobValueTextView.isVisible = isVisible
        }
    }

    private fun showLoadingState() {
        setViewsVisibility(progressBarVisibility = true)
        setUserDataVisibility(false)
    }

    private fun showSuccessState() {
        setViewsVisibility()
        setUserDataVisibility(true)

        with(binding.userDetailsLayout) {
            com.bumptech.glide.Glide.with(photoImageView)
                .load(user.large)
                .placeholder(com.tprobius.randomuserapp.R.drawable.ic_image_placeholder)
                .transform(RoundedCorners(CORNER_RADIUS))
                .into(photoImageView)
            userNameTextView.text = "${user.first} ${user.last}"
            phoneValueTextView.text = user.phone
            emailValueTextView.text = user.email
            addressValueTextView.text =
                "${user.country}, ${user.city}, ${user.street}, ${user.number}"
            timezoneValueTextView.text = user.timezone
            dobValueTextView.text = user.date
        }
    }

    private fun showErrorState() {
        setViewsVisibility(
            errorImageViewVisibility = true,
            errorTextViewVisibility = true,
            tryAgainButtonVisibility = true
        )
        setUserDataVisibility(false)
    }

    private fun setOnPhoneNumberClick() {
        binding.userDetailsLayout.phoneValueTextView.setOnClickListener {
            val telIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("tel:${user.phone}")
            }
            context?.startActivity(telIntent)
        }
    }

    private fun setOnEmailClick() {
        binding.userDetailsLayout.emailValueTextView.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, user.email)
            }
            context?.startActivity(emailIntent)
        }
    }

    private fun setOnLocationClick() {
        binding.userDetailsLayout.addressValueTextView.setOnClickListener {
            val mapIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("geo:${user.latitude}," + "${user.longitude}")
            }
            context?.startActivity(mapIntent)
        }
    }

    private fun setOnBackClick() {
        binding.topAppBar.setNavigationOnClickListener {
            viewModel.getUsersList()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val CORNER_RADIUS = 500
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