package io.mountx.common.app

import android.content.res.Resources
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.Window
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import io.mountx.common.rom.RomCompatHelper

/**
 * @author Ha Sang
 * Created on 2020/4/2
 */
open class MxActivity : LogActivity() {

    protected fun requestDefaultStatusBar() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                window.setLightStatusBar(false)
            }
            RomCompatHelper.isLightStatusBarSupportedByRom -> {
                RomCompatHelper.requestDefaultRomStatusBar(this)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                theme.colorPrimaryDark?.also {
                    window.statusBarColor = it
                }
            }
            else -> {
                //ignore
            }
        }
    }

    protected fun requestLightStatusBar() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                window.setLightStatusBar(true)
            }
            RomCompatHelper.isLightStatusBarSupportedByRom -> {
                RomCompatHelper.requestLightRomStatusBar(this)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                theme.colorPrimaryDark?.also {
                    window.statusBarColor = it
                }
            }
            else -> {
                //ignore
            }
        }
    }

    private val Resources.Theme.colorPrimaryDark: Int?
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        @ColorInt
        get() {
            val outValue = TypedValue()
            val resolved = resolveAttribute(android.R.attr.colorPrimaryDark, outValue, true)
            return if (resolved && outValue.type >= TypedValue.TYPE_FIRST_COLOR_INT && outValue.type <= TypedValue.TYPE_LAST_COLOR_INT) {
                outValue.data
            } else {
                null
            }
        }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun Window.setLightStatusBar(isLight: Boolean) {
        decorView.apply {
            systemUiVisibility = if (isLight) {
                systemUiVisibility.or(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            } else {
                systemUiVisibility.and(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv())
            }
        }
    }

    protected fun requestLayoutFullscreenStable() {
        window.decorView.apply {
            systemUiVisibility = systemUiVisibility or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN_STABLE
        }
    }

    companion object {
        private val SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN_STABLE
            get() = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}