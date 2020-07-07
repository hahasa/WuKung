package io.mountx.wukung.main.ks

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author hhs
 * Created on 2020/4/3
 */
class KsMainViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentPagers = arrayOf(
        FragmentPager("Nearby", KsNearbyFragment.newInstance()),
        FragmentPager("Follow", KsFollowFragment.newInstance()),
        FragmentPager("Trend", KsTrendFragment.newInstance())
    )

    private class FragmentPager(
        val title: CharSequence,
        val fragment: Fragment
    )

    override fun getItem(position: Int): Fragment {
        return fragmentPagers[position].fragment
    }

    override fun getCount(): Int = fragmentPagers.size

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentPagers[position].title
    }

    companion object {
        const val PAGERS_COUNT = 3
    }
}