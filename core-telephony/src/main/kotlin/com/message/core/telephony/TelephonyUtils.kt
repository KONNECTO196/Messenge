/*
 * MIT License
 * Copyright (c) 2026 Message App
 * See LICENSE file in root directory for full license text.
 */

package com.message.core.telephony

import android.content.Context
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import androidx.annotation.RequiresPermission
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TelephonyUtils @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val telephonyManager: TelephonyManager
        get() = context.getSystemService(TelephonyManager::class.java)

    private val subscriptionManager: SubscriptionManager
        get() = context.getSystemService(SubscriptionManager::class.java)

    fun isSimReady(): Boolean =
        telephonyManager.simState == TelephonyManager.SIM_STATE_READY

    fun isDualSim(): Boolean = getActiveSubscriptions().size >= 2

    @RequiresPermission(android.Manifest.permission.READ_PHONE_STATE)
    fun getActiveSubscriptions(): List<SubscriptionInfo> =
        subscriptionManager.activeSubscriptionInfoList ?: emptyList()

    @RequiresPermission(android.Manifest.permission.READ_PHONE_STATE)
    fun getDefaultSubscriptionId(): Int =
        SubscriptionManager.getDefaultSmsSubscriptionId()

    fun getSimCountryCode(): String =
        telephonyManager.simCountryIso?.uppercase() ?: "IN"

    fun canSendSms(): Boolean =
        telephonyManager.phoneType != TelephonyManager.PHONE_TYPE_NONE
}