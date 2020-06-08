package io.mountx.wukung.lists

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import io.mountx.common.app.LogActivity
import io.mountx.wukung.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.android.synthetic.main.activity_list_with_conditions.*
import kotlinx.android.synthetic.main.activity_main.toolbar

/**
 * @author Haha Sang
 * Created on 2019/7/18
 */

private const val SORT_KEY = "sort_key"
private const val FILTER_KEY = "filter_key"

class ListWithConditionsActivity : LogActivity(), TabLayout.OnTabSelectedListener {

    private var filterParam: FilterPopupWindow.FilterParam? = null
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_with_conditions)
        setSupportActionBar(toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        initSortTab()
        initFilter()
    }

    private fun initSortTab() {
        tl_sort.addSortTabs()
        tl_sort.addOnTabSelectedListener(this)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        if (tab?.isPriceTab() == true) {
            tab.togglePriceSortStatus()
            refreshList()
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        if (tab?.isPriceTab() == true) {
            tab.refreshPriceSortStatus(PriceSortStatus.DEFAULT)
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (tab?.isPriceTab() == true) {
            tab.refreshPriceSortStatus(PriceSortStatus.ASC)
        }
        refreshList()
    }

    private fun initFilter() {
        ctv_filter.setOnClickListener {
            showFilterPopupWindow(it)
        }
    }

    private fun showFilterPopupWindow(target: View) {
        disposable = FilterPopupWindow(this, filterParam)
                .showAsMaybe(target)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    filterParam = it
                    refreshFilterButton()
                }, {
                    //ignore
                }, {
                    //click popupWindow outside cancel
                })
    }

    private fun refreshFilterButton() {
        ctv_filter.isChecked = filterParam.isNotEmpty()
    }

    private fun FilterPopupWindow.FilterParam?.isNotEmpty(): Boolean {
        return !this?.city.isNullOrEmpty()
    }

    private fun refreshList() {
        Toast.makeText(baseContext, tl_sort.getCurrentSortName(), Toast.LENGTH_SHORT).show()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.getString(FILTER_KEY)?.let {
            filterParam = Gson().fromJson(it, FilterPopupWindow.FilterParam::class.java)
            refreshFilterButton()
        }
        savedInstanceState?.getString(SORT_KEY)?.let {
            tl_sort.removeOnTabSelectedListener(this)
            tl_sort.selectTab(it)
            tl_sort.addOnTabSelectedListener(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tl_sort.removeOnTabSelectedListener(this)
        disposable?.takeUnless { it.isDisposed }?.dispose()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        tl_sort?.getCurrentSort()?.let {
            outState.putString(SORT_KEY, it)
        }
        filterParam?.let {
            outState.putString(FILTER_KEY, Gson().toJson(it))
        }
    }
}