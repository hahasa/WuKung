package io.mountx.wukung.lists

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import io.mountx.wukung.R
import rx.Observable
import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject

/**
 * @author hhs
 * Created on 2019-07-25
 */
class FilterPopupWindow(context: Context, private val filterParam: FilterParam? = null) :
    PopupWindow(
        LayoutInflater.from(context).inflate(R.layout.popup_window_list_filter, null),
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT,
        true
    ) {

    private val subject: SerializedSubject<FilterResult, FilterResult>
    private var radioGroup: RadioGroup? = null
    private var dismissFromConfirm: Boolean? = null

    init {
        setBackgroundDrawable(ContextCompat.getDrawable(context, android.R.color.transparent))
        val publishSubject = PublishSubject.create<FilterResult>()
        subject = SerializedSubject(publishSubject)
        initView()
    }

    private fun initView() {
        radioGroup = contentView.findViewById<RadioGroup>(R.id.radio_group)
        radioGroup?.apply {
            val checkedRadioButtonId = when (filterParam?.city) {
                SH -> R.id.rb_sh
                SZ -> R.id.rb_sz
                BJ -> R.id.rb_bj
                else -> null
            }
            checkedRadioButtonId?.let {
                check(it)
            }
        }
        contentView?.findViewById<TextView>(R.id.tv_btn_reset)?.setOnClickListener {
            radioGroup?.clearCheck()
        }
        contentView?.findViewById<TextView>(R.id.tv_btn_confirm)?.setOnClickListener {
            dismissFromConfirm = true
            dismiss()
        }
    }

    fun showAsObservable(view: View): Observable<FilterResult> {
        showAsDropDown(view)
        return subject
    }

    override fun dismiss() {
        super.dismiss()
        subject.onNext(getFilterResult())
        subject.onCompleted()
    }

    private fun getFilterResult(): FilterResult {
        return if (dismissFromConfirm == true) {
            FilterResult(true, FilterParam(getCheckedId()))
        } else {
            FilterResult()
        }
    }

    private fun getCheckedId(): String? {
        return when (radioGroup?.checkedRadioButtonId) {
            R.id.rb_sh -> SH
            R.id.rb_sz -> SZ
            R.id.rb_bj -> BJ
            else -> null
        }
    }

    companion object {
        const val SH = "sh"
        const val SZ = "sz"
        const val BJ = "bj"
    }

    data class FilterParam(
        val city: String?
    )

    data class FilterResult(
        val dismissFromConfirm: Boolean = false,
        val param: FilterParam? = null
    )
}