package io.mountx.wukung.settings

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

object WuKungSettingsHelper {

    fun init(context: Context) {
        context.getSettingsSp()
            .getInt(SP_KEY_NIGHT_MODE, AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)
            .also {
                if (it != AppCompatDelegate.MODE_NIGHT_UNSPECIFIED) {
                    AppCompatDelegate.setDefaultNightMode(it)
                }
            }
    }
}