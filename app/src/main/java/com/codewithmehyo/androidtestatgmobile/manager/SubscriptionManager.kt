package com.codewithmehyo.androidtestatgmobile.manager

import android.content.Context
import androidx.core.content.edit

/**
 * Manages the user's subscription status.
 *
 * @property context The application context.
 */
class SubscriptionManager(private val context: Context) {
    // The shared preferences file to store the subscription status.
    private val prefs = context.getSharedPreferences(USER_PREFS_FILE, Context.MODE_PRIVATE)

    /**
     * Whether the user is subscribed or not.
     */
    var isSubscribed: Boolean
        get() = prefs.getBoolean(SUBSCRIPTION_STATUS_KEY, SUBSCRIPTION_STATUS_DEFAULT)
        set(value) = prefs.edit { putBoolean(SUBSCRIPTION_STATUS_KEY, value) }

    companion object {
        private const val SUBSCRIPTION_STATUS_KEY = "is_subscribed"
        private const val SUBSCRIPTION_STATUS_DEFAULT = false
        private const val USER_PREFS_FILE = "user_prefs"
    }
}