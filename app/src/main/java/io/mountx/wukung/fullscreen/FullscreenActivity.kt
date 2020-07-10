package io.mountx.wukung.fullscreen

import android.os.Bundle
import io.mountx.common.app.MxActivity
import io.mountx.wukung.R

class FullscreenActivity : MxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
    }
}