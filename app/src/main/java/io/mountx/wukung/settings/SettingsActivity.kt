package io.mountx.wukung.settings

import android.os.Bundle
import io.mountx.common.app.MxActivity
import io.mountx.wukung.R

class SettingsActivity : MxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity_settings)
        requestLightStatusBar()
        initMainFragment()
    }

    private fun initMainFragment() {
        if (supportFragmentManager.fragments.size == 0) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, SettingsFragment())
                commitAllowingStateLoss()
            }
        }
    }

    fun gotoSetNightModeFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, SetNightModeFragment())
            addToBackStack(null)
            commitAllowingStateLoss()
        }
    }
}