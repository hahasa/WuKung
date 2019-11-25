package io.mountx.wukung.main

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import io.mountx.common.app.LogActivity
import io.mountx.wukung.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : LogActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        initToolbar()
        initActivityEntrances()
    }

    private fun initActivityEntrances() {
        val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        val activitiesWithoutMainActivity = packageInfo.activities?.filter {
            it.name != MainActivity::class.java.name
        }
        recycler_view_activity_entrances.adapter = ActivityEntrancesAdapter(activitiesWithoutMainActivity)
    }

    private fun initToolbar() {
        setTitle(R.string.main_activity_title)
    }
}
