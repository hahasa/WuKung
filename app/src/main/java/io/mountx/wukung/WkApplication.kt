package io.mountx.wukung

import android.app.Application
import io.mountx.common.rom.RomCompatHelper

class WkApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RomCompatHelper.init()
    }
}