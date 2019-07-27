package io.mountx.wukung.lists

import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatCheckedTextView
import com.google.android.material.tabs.TabLayout
import io.mountx.wukung.R

/**
 * @author Haha Sang
 * Created on 2019-06-28
 */
private const val SORT_BY_DEFAULT = "default"
private const val SORT_BY_SCORE = "score"
private const val SORT_BY_DISTANCE = "distance"
private const val SORT_BY_SALES = "sales"
private const val SORT_BY_PRICE_DESC = "price"
private const val SORT_BY_PRICE_ASC = "price_asc"

enum class PriceSortStatus {
    DEFAULT,
    ASC,
    DESC
}

fun TabLayout.Tab.isPriceTab(): Boolean {
    return text == parent.context.getString(R.string.sort_by_price)
}

private fun TabLayout.Tab.getTabCustomAppCompatCheckedTextView(): AppCompatCheckedTextView? {
    return (customView as? AppCompatCheckedTextView)
}

fun TabLayout.Tab.refreshPriceSortStatus(priceSortStatus: PriceSortStatus) {
    getTabCustomAppCompatCheckedTextView()?.let {
        when (priceSortStatus) {
            PriceSortStatus.DEFAULT -> {
                tag = null
                it.isChecked = false
            }
            PriceSortStatus.DESC -> {
                tag = SORT_BY_PRICE_DESC
                it.isChecked = true
            }
            PriceSortStatus.ASC -> {
                tag = SORT_BY_PRICE_ASC
                it.isChecked = false
            }
        }
    }
}

fun TabLayout.Tab.togglePriceSortStatus() {
    when (tag) {
        SORT_BY_PRICE_DESC -> refreshPriceSortStatus(PriceSortStatus.ASC)
        SORT_BY_PRICE_ASC -> refreshPriceSortStatus(PriceSortStatus.DESC)
    }
}

fun TabLayout.addSortTabs() {
    val defaultSortTab = newSortTab(SORT_BY_DEFAULT, R.string.sort_default)
    addTab(defaultSortTab)

    val scoreSortTab = newSortTab(SORT_BY_SCORE, R.string.sort_by_score)
    addTab(scoreSortTab)

    val salesSortTab = newSortTab(SORT_BY_SALES, R.string.sort_by_sales)
    addTab(salesSortTab)

    val distanceSortTab = newSortTab(SORT_BY_DISTANCE, R.string.sort_by_distance)
    addTab(distanceSortTab)

    val priceSortTab = newSortTab(null, R.string.sort_by_price)
    priceSortTab.setCustomView(R.layout.tab_price_sort)

    addTab(priceSortTab)
}

private fun TabLayout.newSortTab(sort: String?, @StringRes sortNameStrRes: Int): TabLayout.Tab {
    return newSortTab(sort, context.getString(sortNameStrRes))
}

private fun TabLayout.newSortTab(sort: String?, sortName: String): TabLayout.Tab {
    return newTab().apply {
        text = sortName
        tag = sort
    }
}

fun TabLayout.selectTab(savedSort: String?) {
    savedSort?.let {
        for (i in 0..tabCount) {
            getTabAt(i)?.run {
                var consumed = false
                when {
                    tag == it -> {
                        select()
                        consumed = true
                    }
                    isPriceTab() -> {
                        consumed = dispatchPriceTabSelect(savedSort)
                    }
                }
                if (consumed) {
                    return@let
                }
            }
        }
    }
}

private fun TabLayout.Tab.dispatchPriceTabSelect(savedSort: String?): Boolean {
    if (!isPriceTab()) return false
    return when (savedSort) {
        SORT_BY_PRICE_DESC -> {
            refreshPriceSortStatus(PriceSortStatus.DESC)
            select()
            true
        }
        SORT_BY_PRICE_ASC -> {
            refreshPriceSortStatus(PriceSortStatus.ASC)
            select()
            true
        }
        else -> false
    }
}

fun TabLayout.getCurrentSort(): String? {
    return getTabAt(selectedTabPosition)?.tag as? String
}

fun TabLayout.getCurrentSortName(): String? {
    return getTabAt(selectedTabPosition)?.text as? String
}