package io.mountx.wukung

import android.app.Application
import io.mountx.common.rom.RomCompatHelper
import io.mountx.wukung.settings.WuKungSettingsHelper

class WkApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RomCompatHelper.init()
        WuKungSettingsHelper.init(applicationContext)
    }
}