package io.mountx.wukung.main.ks

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.PagerSnapHelper
import io.mountx.common.app.MxFragment
import io.mountx.wukung.R
import kotlinx.android.synthetic.main.ks_fragment_trend.*

/**
 * @author hhs
 * Created on 2020/4/3
 */
class KsTrendFragment : MxFragment(R.layout.ks_fragment_trend) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_feeds.apply {
            PagerSnapHelper().attachToRecyclerView(this)
            adapter = KsTrendAdapter(20)
        }
    }

    companion object {
        fun newInstance() = KsTrendFragment()
    }
}