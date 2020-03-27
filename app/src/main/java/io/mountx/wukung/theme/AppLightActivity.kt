package io.mountx.wukung.theme

import android.os.Build
import android.os.Bundle
import android.view.View
import io.mountx.common.app.LogActivity
import io.mountx.wukung.R
import kotlinx.android.synthetic.main.activity_widget_set.*

/**
 * @author hhs
 * Created on 2020/3/25
 */
class AppLightActivity : LogActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget_set)
        setSupportActionBar(toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}