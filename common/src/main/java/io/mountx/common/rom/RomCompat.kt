package io.mountx.common.rom

import android.app.Activity
import android.os.Build

object RomCompatHelper {

    private var isMiui = false

    fun init() {
        if (isLollipop()) {
            MxProperties().apply {
                isMiui = isMiui()
            }
        }
    }

    val isLightStatusBarSupportedByRom: Boolean
        get() {
            return when {
                isMiui -> isLightStatusBarSupportedByMiui
                else -> false
            }
        }

    fun requestLightRomStatusBar(activity: Activity) {
        if (!isLightStatusBarSupportedByRom) return
        when {
            isMiui -> requestLightMiuiStatusBar(activity)
        }
    }

    fun requestDefaultRomStatusBar(activity: Activity) {
        if (!isLightStatusBarSupportedByRom) return
        when {
            isMiui -> requestDefaultMiuiStatusBar(activity)
        }
    }
}

fun isLollipop() = Build.VERSION.SDK_INT.let {
    it == Build.VERSION_CODES.LOLLIPOP || it == Build.VERSION_CODES.LOLLIPOP_MR1
}