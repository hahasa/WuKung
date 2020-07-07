package io.mountx.common.rom

import android.app.Activity
import android.view.Window

private const val KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"

internal val isLightStatusBarSupportedByMiui: Boolean
    get() = isLollipop()

internal fun MxProperties.isMiui() = !getSysPropValue(KEY_MIUI_VERSION_NAME).isNullOrEmpty()

private fun setMiuiStatusBar(activity: Activity, isLight: Boolean) {
    MIUI_FLAG_LIGHT_STATUS_BAR?.let {
        try {
            val flag = if (isLight) it else 0
            activity.window.setMiuiExtraFlags(flag, it)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

private fun Window.setMiuiExtraFlags(flag: Int, mask: Int) {
    javaClass.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
        .invoke(this, flag, mask)
}

private val MIUI_FLAG_LIGHT_STATUS_BAR: Int?
    get() = try {
        Class.forName("android.view.MiuiWindowManager\$LayoutParams").run {
            getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(this)
        }
    } catch (e: ClassNotFoundException) {
        null
    } catch (e: NoSuchFieldException) {
        null
    } catch (e: IllegalArgumentException) {
        null
    } catch (e: IllegalAccessException) {
        null
    }

internal fun requestLightMiuiStatusBar(activity: Activity) {
    setMiuiStatusBar(activity, true)
}

internal fun requestDefaultMiuiStatusBar(activity: Activity) {
    setMiuiStatusBar(activity, false)
}