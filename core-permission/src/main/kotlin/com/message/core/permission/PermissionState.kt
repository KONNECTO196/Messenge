/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.core.permission

data class PermissionState(
    val smsGranted:      Boolean = false,
    val contactsGranted: Boolean = false,
    val phoneGranted:    Boolean = false,
    val cameraGranted:   Boolean = false,
    val storageGranted:  Boolean = false,
    val notifGranted:    Boolean = false,
) {
    val allSmsPermissionsGranted: Boolean
        get() = smsGranted && phoneGranted

    val allGranted: Boolean
        get() = smsGranted && contactsGranted && phoneGranted
}