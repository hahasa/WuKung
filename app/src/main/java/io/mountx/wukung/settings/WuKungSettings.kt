package io.mountx.wukung.settings

import android.content.Context
import android.content.SharedPreferences

private fun Context.getSettingsSpFileName() =
    "$packageName.settings"

fun Context.getSettingsSp(): SharedPreferences =
    getSharedPreferences(getSettingsSpFileName(), Context.MODE_PRIVATE)


const val SP_KEY_NIGHT_MODE = "night_mode"