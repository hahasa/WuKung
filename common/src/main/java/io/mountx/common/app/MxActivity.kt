package io.mountx.common.app

import android.os.Build
import android.view.View

/**
 * @author Ha Sang
 * Created on 2020/4/2
 */
open class MxActivity : LogActivity() {

    protected fun requestDefaultStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.apply {
                systemUiVisibility =
                    systemUiVisibility.and(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv())
            }
        }
    }

    protected fun requestLightStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.apply {
                systemUiVisibility = systemUiVisibility.or(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
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