package com.tprobius.randomuserapp.presentation.userslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tprobius.randomuserapp.databinding.FragmentUsersListBinding
import com.tprobius.randomuserapp.domain.entities.RandomUser
import com.tprobius.randomuserapp.presentation.userslist.userslistadapter.UsersListAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class UsersListFragment : Fragment() {

    private var _binding: FragmentUsersListBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding isn't initialized" }

    private val viewModel: UsersListViewModel by viewModel()

    private var usersListAdapter by Delegates.notNull<UsersListAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRecipeList(150)
        viewModel.state.observe(viewLifecycleOwner, ::handleState)

        setUsersListAdapter()
        setOnTryAgainClick()
    }

    private fun handleState(state: UserListState) {
        when (state) {
            UserListState.Initial -> showInitialState()
            UserListState.Loading -> showLoadingState()
            is UserListState.Success -> showSuccessState(state.usersList)
            UserListState.Error -> showErrorState()
        }
    }

    private fun showInitialState() {
        with(binding) {
            progressBar.isVisible = false
            errorImageView.isVisible = false
            errorTextView.isVisible = false
            tryAgainButton.isVisible = false
            usersListRecyclerView.isVisible = false
        }
    }

    private fun showLoadingState() {
        with(binding) {
            progressBar.isVisible = true
            errorImageView.isVisible = false
            errorTextView.isVisible = false
            tryAgainButton.isVisible = false
            usersListRecyclerView.isVisible = false
        }
    }

    private fun showSuccessState(usersList: List<RandomUser>) {
        with(binding) {
            progressBar.isVisible = false
            errorImageView.isVisible = false
            errorTextView.isVisible = false
            tryAgainButton.isVisible = false
            usersListRecyclerView.isVisible = true
        }

        viewLifecycleOwner.lifecycleScope.launch {
            usersListAdapter.submitList(usersList)
        }
    }

    private fun showErrorState() {
        with(binding) {
            progressBar.isVisible = false
            errorImageView.isVisible = true
            errorTextView.isVisible = true
            tryAgainButton.isVisible = true
            usersListRecyclerView.isVisible = false
        }
    }

    private fun setUsersListAdapter() {
        usersListAdapter = UsersListAdapter(
            onClickListener = {
                setOnClickListener(it)
            },
            onPhoneNumberClick = {
                setOnPhoneNumberClick(it)
            },
            onEmailClick = {
                setOnEmailClick(it)
            },
            onLocationClick = {
                setOnLocationClick(it)
            }
        )
        binding.usersListRecyclerView.adapter = usersListAdapter
    }

    private fun setOnClickListener(user: RandomUser) {

    }

    private fun setOnPhoneNumberClick(user: RandomUser) {

    }

    fun setOnEmailClick(user: RandomUser) {

    }

    private fun setOnLocationClick(user: RandomUser) {

    }

    private fun setOnTryAgainClick() {

    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}