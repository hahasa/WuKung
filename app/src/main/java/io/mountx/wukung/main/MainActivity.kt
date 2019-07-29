package io.mountx.wukung.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import io.mountx.common.app.LogActivity
import io.mountx.wukung.R
import io.mountx.wukung.layout.LayoutActivity
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
        findViewById<TextView>(R.id.tv_hello).setOnClickListener {
            val intent = Intent(this, LayoutActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initToolbar() {
        setTitle(R.string.main_activity_title)
    }
}
