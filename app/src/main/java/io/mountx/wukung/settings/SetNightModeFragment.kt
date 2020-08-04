package io.mountx.wukung.settings

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import io.mountx.common.app.MxFragment
import io.mountx.wukung.R
import kotlinx.android.synthetic.main.settings_fragment_set_night_mode.*

class SetNightModeFragment : MxFragment(R.layout.settings_fragment_set_night_mode) {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPreferences = context.getSettingsSp()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compatSetButtonNullPerL()
        initSwitchFollowSystem()
        initDayNightMode()
        btn_save.setOnClickListener {
            performClickButtonSave()
        }
    }

    private fun compatSetButtonNullPerL() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            radio_button_night_mode_day.buttonDrawable = ColorDrawable()
            radio_button_night_mode_night.buttonDrawable = ColorDrawable()
        }
    }

    private fun initSwitchFollowSystem() {
        val isFollowSystemSupported = isFollowSystemSupported()
        val followSystemViewsVisibility = if (isFollowSystemSupported) View.VISIBLE else View.GONE
        switch_follow_system.visibility = followSystemViewsVisibility
        text_view_set_manually.visibility = followSystemViewsVisibility
        if (isFollowSystemSupported) {
            switch_follow_system.setOnCheckedChangeListener { _, isChecked ->
                performClickSwitchFollowSystem(isChecked)
            }
        }
    }

    private fun initDayNightMode() {
        when (getSetNightModeOrDefault()) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                if (switch_follow_system.visibility == View.VISIBLE) {
                    switch_follow_system.isChecked = false
                }
                radio_group_modes.check(R.id.radio_button_night_mode_night)
            }
            AppCompatDelegate.MODE_NIGHT_NO -> {
                if (switch_follow_system.visibility == View.VISIBLE) {
                    switch_follow_system.isChecked = false
                }
                radio_group_modes.check(R.id.radio_button_night_mode_day)
            }
            else -> {
                if (switch_follow_system.visibility == View.VISIBLE) {
                    switch_follow_system.isChecked = true
                } else {//downgrade android version
                    radio_group_modes.check(R.id.radio_button_night_mode_day)
                }
            }
        }
    }

    private fun getSetNightModeOrDefault(): Int {
        val defaultNightMode =
            if (isFollowSystemSupported()) AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            else AppCompatDelegate.MODE_NIGHT_NO
        return sharedPreferences.getInt(SP_KEY_NIGHT_MODE, defaultNightMode)
    }

    private fun isFollowSystemSupported() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    private fun performClickSwitchFollowSystem(isChecked: Boolean) {
        val visibility = if (isChecked) View.GONE else View.VISIBLE
        text_view_set_manually.visibility = visibility
        radio_group_modes.visibility = visibility
        if (!isChecked) {
            radio_group_modes.check(R.id.radio_button_night_mode_day)
        }
    }

    private fun performClickButtonSave() {
        val newNightMode = getNewNightMode()
        if (newNightMode == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED) {
            return
        }
        sharedPreferences.edit().apply {
            putInt(SP_KEY_NIGHT_MODE, newNightMode)
            apply()
        }
        val currentNightMode = currentNightMode()
        if (currentNightMode != newNightMode) {
            AppCompatDelegate.setDefaultNightMode(newNightMode)
        }
        activity?.onBackPressed()
    }

    private fun currentNightMode() =
        requireContext().resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

    private fun getNewNightMode(): Int {
        if (switch_follow_system.run { visibility == View.VISIBLE && isChecked }) {
            return AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
        return when (radio_group_modes.checkedRadioButtonId) {
            R.id.radio_button_night_mode_day -> AppCompatDelegate.MODE_NIGHT_NO
            R.id.radio_button_night_mode_night -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_UNSPECIFIED
        }
    }
}