package io.mountx.wukung.settings

import android.os.Bundle
import android.view.View
import io.mountx.common.app.MxFragment
import io.mountx.wukung.R
import kotlinx.android.synthetic.main.settings_fragment_settings.*

class SettingsFragment : MxFragment(R.layout.settings_fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_night_mode?.setOnClickListener {
            (activity as? SettingsActivity)?.gotoSetNightModeFragment()
        }
    }
}