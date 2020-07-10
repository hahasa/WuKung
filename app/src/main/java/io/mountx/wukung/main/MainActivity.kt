package io.mountx.wukung.main

import android.content.pm.PackageManager
import android.os.Bundle
import io.mountx.common.app.MxActivity
import io.mountx.wukung.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        requestLightStatusBar()
        initToolbar()
        initActivityEntrances()
    }

    private fun initActivityEntrances() {
        val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        val activitiesWithoutMainActivity = packageInfo.activities?.filter {
            it.name != MainActivity::class.java.name
        }
        recycler_view_activity_entrances.adapter =
            ActivityEntrancesAdapter(activitiesWithoutMainActivity)
    }

    private fun initToolbar() {
        setTitle(R.string.main_activity_title)
    }
}
