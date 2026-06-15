/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.core.sms

import android.content.Context
import android.database.ContentObserver
import android.os.Handler
import android.os.Looper
import android.provider.Telephony
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmsObserver @Inject constructor(
    @ApplicationContext private val context: Context,
    private val smsSyncManager: SmsSyncManager,
) {

    private val scope     = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var isStarted = false

    private val observer = object : ContentObserver(Handler(Looper.getMainLooper())) {
        override fun onChange(selfChange: Boolean) {
            scope.launch { smsSyncManager.syncAll() }
        }
    }

    fun start() {
        if (isStarted) return
        context.contentResolver.registerContentObserver(
            Telephony.Sms.CONTENT_URI,
            true,
            observer
        )
        context.contentResolver.registerContentObserver(
            Telephony.Mms.CONTENT_URI,
            true,
            observer
        )
        isStarted = true
    }

    fun stop() {
        if (!isStarted) return
        context.contentResolver.unregisterContentObserver(observer)
        isStarted = false
    }
}