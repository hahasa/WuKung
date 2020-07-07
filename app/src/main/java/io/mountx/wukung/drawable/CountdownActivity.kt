package io.mountx.wukung.drawable

import android.os.Bundle
import io.mountx.common.app.MxActivity
import io.mountx.wukung.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Ha Sang
 * Created on 2019-07-31
 */
class CountdownActivity : MxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countdown)
        setSupportActionBar(toolbar)
        requestLightStatusBar()
    }
}