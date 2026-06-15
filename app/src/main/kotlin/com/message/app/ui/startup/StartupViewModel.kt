/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.app.ui.startup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.message.core.permission.PermissionState
import com.message.core.telephony.SmsRoleManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    private val smsRoleManager: SmsRoleManager,
) : ViewModel() {

    sealed class Event {
        data object RequestDefaultSms  : Event()
        data object RequestPermissions : Event()
        data object Ready              : Event()
    }

    private val _isLoading   = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _startupEvent = MutableSharedFlow<Event>(extraBufferCapacity = 1)
    val startupEvent: SharedFlow<Event> = _startupEvent.asSharedFlow()

    init {
        checkStartupRequirements()
    }

    private fun checkStartupRequirements() {
        viewModelScope.launch {
            when {
                !smsRoleManager.isDefaultSmsApp() ->
                    _startupEvent.emit(Event.RequestDefaultSms)
                else ->
                    _startupEvent.emit(Event.RequestPermissions)
            }
            _isLoading.value = false
        }
    }

    fun onDefaultSmsResult(isDefault: Boolean) {
        viewModelScope.launch {
            if (isDefault) {
                _startupEvent.emit(Event.RequestPermissions)
            }
        }
    }

    fun onPermissionsResult(state: PermissionState) {
        viewModelScope.launch {
            if (state.allSmsPermissionsGranted) {
                _startupEvent.emit(Event.Ready)
            }
        }
    }
}