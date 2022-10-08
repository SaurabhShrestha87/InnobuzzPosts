package com.innobuzz.postapp

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Context
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.text.TextUtils.SimpleStringSplitter
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class MyAccessibilityService : AccessibilityService() {
    private val info = AccessibilityServiceInfo()
    override fun onAccessibilityEvent(accessibilityEvent: AccessibilityEvent) {
        Log.d(TAG, "onAccessibilityEvent")
        val sourcePackageName = accessibilityEvent.packageName as String
        Log.d(TAG, "sourcePackageName:$sourcePackageName")
        Log.d(TAG, "parcelable:" + accessibilityEvent.text.toString())
        if (accessibilityEvent.packageName != null && accessibilityEvent.packageName != "com.whatsapp") {
            Log.d(TAGEVENTS, "Whatsapp Opened")
            Toast.makeText(this, "Whatsapp Opened", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onInterrupt() {}
    public override fun onServiceConnected() {
        // Set the type of events that this service wants to listen to.
        //Others won't be passed to this service.
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
        info.notificationTimeout = 100
        this.serviceInfo = info
    }

    companion object {
        private const val TAG = "MyAccessibilityService"
        private const val TAGEVENTS = "TAGEVENTS"

        /**
         * Check if Accessibility Service is enabled.
         *
         * @param mContext
         * @return `true` if Accessibility Service is ON, otherwise `false`
         */

        fun isAccessibilitySettingsOn(mContext: Context): Boolean {
            var accessibilityEnabled = 0
            val service = "com.innobuzz.postapp.MyAccessibilityService"
            var accessibilityFound = false

            try {
                accessibilityEnabled = Settings.Secure.getInt(
                    mContext.applicationContext.contentResolver,
                    Settings.Secure.ACCESSIBILITY_ENABLED)
                Log.v(TAG, "accessibilityEnabled = $accessibilityEnabled")
            } catch (e: SettingNotFoundException) {
                Log.e(TAG, "Error finding setting, default accessibility to not found: "
                        + e.message)
            }
            val mStringColonSplitter = SimpleStringSplitter(':')
            if (accessibilityEnabled == 1) {
                val settingValue = Settings.Secure.getString(
                    mContext.applicationContext.contentResolver,
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
                if (settingValue != null) {
                    mStringColonSplitter.setString(settingValue)
                    while (mStringColonSplitter.hasNext()) {
                        val accessabilityService = mStringColonSplitter.next()
                        if (accessabilityService.equals(service, ignoreCase = true)) {
                            accessibilityFound = true
                        }
                    }
                }
            } else {
                accessibilityFound = false
                Log.v(TAG, "***ACCESSIBILIY IS DISABLED***")
            }
            return accessibilityFound
        }
    }
}