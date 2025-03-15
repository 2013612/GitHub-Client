package com.example.githubclient.user.presentation.detail.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.githubclient.common.data.model.DataError
import com.example.githubclient.common.domain.model.onError
import com.example.githubclient.common.domain.model.onSuccess
import com.example.githubclient.common.presentation.model.PaginationState
import com.example.githubclient.user.domain.IUserRepository
import com.example.githubclient.user.presentation.detail.model.UserDetailScreenEvent
import com.example.githubclient.user.presentation.detail.model.UserDetailScreenState
import com.example.githubclient.user.presentation.detail.model.UserDetailViewModelEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val userRepository: IUserRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val routeArguments = savedStateHandle.toRoute<NavUserDetailScreen>()

    private val _screenStateFlow = MutableStateFlow(UserDetailScreenState())
    val screenStateFlow = _screenStateFlow.asStateFlow()

    private val eventsChannel = Channel<UserDetailViewModelEvent>()
    val eventsFlow = eventsChannel.receiveAsFlow()

    private var page: Int = 1

    init {
        fetchUserProfile()
    }

    fun onEvent(event: UserDetailScreenEvent) {
        when (event) {
            UserDetailScreenEvent.OnLoadMore -> fetchUserEvents()
        }
    }

    private fun fetchUserProfile() {
        val userName = routeArguments.userName
        viewModelScope.launch {
            userRepository
                .fetchUserProfile(userName = userName)
                .onSuccess {
                    _screenStateFlow.value = _screenStateFlow.value.copy(profile = it)
                }.onError {
                    println("Error: $it")
                    eventsChannel.send(UserDetailViewModelEvent.OnFetchError(isNoInternetError = it == DataError.Remote.NO_INTERNET))
                }
        }
    }

    private fun fetchUserEvents() {
        val userName = "kenkoooo"
        val paginationState = screenStateFlow.value.paginationState

        when (paginationState) {
            PaginationState.LOADING, PaginationState.PAGINATING -> return
            PaginationState.PAGINATION_EXHAUST -> if (page != 1) return
            PaginationState.IDLE -> {}
        }

        _screenStateFlow.update {
            it.copy(
                paginationState =
                    if (page == 1) {
                        PaginationState.LOADING
                    } else {
                        PaginationState.PAGINATING
                    },
            )
        }
        viewModelScope.launch {
            userRepository
                .fetchUserEvents(userName = userName, page = page, perPage = PAGE_SIZE)
                .onSuccess { newEvents ->
                    _screenStateFlow.update {
                        it.copy(
                            events = it.events + newEvents,
                            paginationState =
                                if (newEvents.isEmpty()) {
                                    PaginationState.PAGINATION_EXHAUST
                                } else {
                                    PaginationState.IDLE
                                },
                        )
                    }
                    page++
                }.onError {
                    println("Error: $it")
                    _screenStateFlow.update {
                        it.copy(
                            paginationState = PaginationState.IDLE,
                        )
                    }
                }
        }
    }

    companion object {
        private const val PAGE_SIZE = 30
    }
}
