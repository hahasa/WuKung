package io.mountx.wukung.theme

import android.os.Bundle
import io.mountx.common.app.MxActivity
import io.mountx.wukung.R
import kotlinx.android.synthetic.main.activity_widget_set.*

/**
 * @author hhs
 * Created on 2020/3/25
 */
class AppLightActivity : MxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget_set)
        setSupportActionBar(toolbar)
        requestLightStatusBar()
    }
}