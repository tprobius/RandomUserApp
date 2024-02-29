package com.tprobius.randomuserapp.presentation.userslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tprobius.randomuserapp.MainActivity
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
    private var layoutManager by Delegates.notNull<GridLayoutManager>()
    private var lastPosition by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lastPosition = requireActivity()
            .getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE)
            .getInt(LAST_POSITION, 0)

        _binding = FragmentUsersListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUsersList(MainActivity.isFirst.last())
        viewModel.state.observe(viewLifecycleOwner, ::handleState)

        setUsersListAdapter()
        setOnTryAgainClick()
        setOnRefreshList()
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
        serViewsVisibility()
    }

    private fun serViewsVisibility(
        progressBarIsVisible: Boolean = false,
        errorImageViewIsVisible: Boolean = false,
        errorTextViewIsVisible: Boolean = false,
        tryAgainButtonIsVisible: Boolean = false,
        usersListRecyclerViewIsVisible: Boolean = false
    ) {
        with(binding) {
            progressBar.isVisible = progressBarIsVisible
            errorImageView.isVisible = errorImageViewIsVisible
            errorTextView.isVisible = errorTextViewIsVisible
            tryAgainButton.isVisible = tryAgainButtonIsVisible
            usersListRecyclerView.isVisible = usersListRecyclerViewIsVisible
        }
    }

    private fun showLoadingState() {
        serViewsVisibility(progressBarIsVisible = true)
    }

    private fun showSuccessState(usersList: List<RandomUser>) {
        requireActivity()
            .getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE).edit()
            .putBoolean(MainActivity.IS_FIRST_ENTRY, false)
            .apply()

        MainActivity.isFirst.add(false)

        serViewsVisibility(usersListRecyclerViewIsVisible = true)

        viewLifecycleOwner.lifecycleScope.launch {
            usersListAdapter.submitList(usersList)
        }

        binding.usersListRecyclerView.scrollToPosition(lastPosition)
    }

    private fun showErrorState() {
        serViewsVisibility(
            errorImageViewIsVisible = true,
            errorTextViewIsVisible = true,
            tryAgainButtonIsVisible = true
        )
    }


    private fun setUsersListAdapter() {
        usersListAdapter = UsersListAdapter(onClickListener = { viewModel.getUserDetails(it) })
        binding.usersListRecyclerView.adapter = usersListAdapter

        layoutManager = GridLayoutManager(requireContext(), 1)
        binding.usersListRecyclerView.layoutManager = layoutManager

        binding.usersListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(usersListRecyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(usersListRecyclerView, newState)
                lastPosition = layoutManager.findFirstVisibleItemPosition()
            }
        })
    }

    private fun setOnTryAgainClick() {
        binding.tryAgainButton.setOnClickListener {
            viewModel.getUsersList(MainActivity.isFirst.last())
        }
    }

    private fun setOnRefreshList() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            usersListAdapter.submitList(emptyList())
            MainActivity.isFirst.add(true)
            viewModel.getUsersList(MainActivity.isFirst.last())
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onPause() {
        super.onPause()
        requireActivity()
            .getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE).edit()
            .putInt(LAST_POSITION, lastPosition)
            .apply()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        const val LAST_POSITION = "last_position"
    }
}