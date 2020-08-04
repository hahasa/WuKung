package io.mountx.wukung.main.ks

import android.os.Bundle
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import io.mountx.common.app.MxActivity
import io.mountx.wukung.R
import kotlinx.android.synthetic.main.ks_activity_main.*
import kotlin.math.max

/**
 * @author hhs
 * Created on 2020/4/3
 */
class KsMainActivity : MxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compatRequestLayoutFullscreenStable()
        setContentView(R.layout.ks_activity_main)
        initContentView()
        initViewPager()
    }

    private fun initContentView() {
        ViewCompat.setOnApplyWindowInsetsListener(content) { v, insets ->
            (v as? ViewGroup)?.apply {
                for (i in 0 until childCount) {
                    ViewCompat.dispatchApplyWindowInsets(getChildAt(i), insets)
                }
            }
            insets.consumeSystemWindowInsets()
        }
    }

    private fun initViewPager() {
        tab_layout?.setupWithViewPager(view_pager)
        view_pager?.offscreenPageLimit = max(1, pagersCount - 1)
        ViewCompat.setOnApplyWindowInsetsListener(view_pager) { v, insets ->
            (v as? ViewGroup)?.apply {
                for (i in 0 until childCount) {
                    ViewCompat.dispatchApplyWindowInsets(getChildAt(i), insets)
                }
            }
            insets.consumeSystemWindowInsets()
        }
        view_pager?.adapter = KsMainViewPagerAdapter(supportFragmentManager)
        view_pager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                //ignore
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //ignore
            }

            override fun onPageSelected(position: Int) {
                if (position == pagersCount - 1) {
                    requestDefaultStatusBar()
                } else {
                    requestLightStatusBar()
                }
            }
        })
        view_pager.currentItem = pagersCount - 1
    }

    private val pagersCount
        get() = KsMainViewPagerAdapter.PAGERS_COUNT
}