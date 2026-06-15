/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.core.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val _state = MutableStateFlow(PermissionState())
    val state: StateFlow<PermissionState> = _state.asStateFlow()

    fun refresh() {
        _state.value = PermissionState(
            smsGranted      = hasPermission(Manifest.permission.SEND_SMS) &&
                              hasPermission(Manifest.permission.READ_SMS) &&
                              hasPermission(Manifest.permission.RECEIVE_SMS),
            contactsGranted = hasPermission(Manifest.permission.READ_CONTACTS),
            phoneGranted    = hasPermission(Manifest.permission.READ_PHONE_STATE),
            cameraGranted   = hasPermission(Manifest.permission.CAMERA),
            storageGranted  = hasStoragePermission(),
            notifGranted    = hasNotificationPermission(),
        )
    }

    fun hasPermission(permission: String): Boolean =
        ContextCompat.checkSelfPermission(context, permission) ==
            PackageManager.PERMISSION_GRANTED

    private fun hasStoragePermission(): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            hasPermission(Manifest.permission.READ_MEDIA_IMAGES) &&
            hasPermission(Manifest.permission.READ_MEDIA_VIDEO)
        } else {
            hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

    private fun hasNotificationPermission(): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            hasPermission(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            true
        }

    val smsPermissions: Array<String> = arrayOf(
        Manifest.permission.SEND_SMS,
        Manifest.permission.READ_SMS,
        Manifest.permission.RECEIVE_SMS,
        Manifest.permission.RECEIVE_MMS,
        Manifest.permission.READ_PHONE_STATE,
    )

    val contactsPermissions: Array<String> = arrayOf(
        Manifest.permission.READ_CONTACTS,
    )

    val storagePermissions: Array<String> =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_AUDIO,
            )
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
}