package io.mountx.wukung.layout

import android.os.Bundle
import io.mountx.common.app.MxActivity
import io.mountx.wukung.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Ha Sang
 * Created on 2019-07-29
 */
class LayoutActivity : MxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        setSupportActionBar(toolbar)
        requestLightStatusBar()
    }
}