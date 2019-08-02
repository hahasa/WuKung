package io.mountx.wukung.drawable

import android.os.Bundle
import android.view.View
import io.mountx.common.app.LogFragment
import io.mountx.wukung.R
import kotlinx.android.synthetic.main.fragment_countdown_progressbar.*
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author Ha Sang
 * Created on 2019-07-31
 */

private const val COUNTDOWN_SECOND = 100

class CountdownProgressbarFragment : LogFragment(R.layout.fragment_countdown_progressbar) {

    private var restSecond = 0
    private var countDownSubscription: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restSecond = COUNTDOWN_SECOND
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress.max = COUNTDOWN_SECOND
        tv_btn_start?.setOnClickListener {
            startCountdownIfNeeded()
        }
        updateCountdownProgressView()
    }

    private fun startCountdownIfNeeded() {
        if (restSecond > 0 && countDownSubscription == null) {
            updateCountdownProgressView()
            countDownSubscription = Observable
                    .interval(1000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .doOnNext {
                        if (restSecond > 0) {
                            restSecond--
                        }
                    }
                    .onBackpressureDrop()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                updateCountdownProgressView()
                                stopCountdownIfNeeded()
                            },
                            {
                                //ignore
                            }
                    )
        }
    }

    private fun updateCountdownProgressView() {
        progress.progress = restSecond
        tv_rest_second?.apply {
            background.level = restSecond * 10000 / COUNTDOWN_SECOND
            text = "${restSecond}s"
        }
    }

    private fun stopCountdownIfNeeded() {
        if (restSecond <= 0) {
            stopCountdown()
        }
    }

    private fun stopCountdown() {
        countDownSubscription?.let {
            if (!it.isUnsubscribed) {
                it.unsubscribe()
            }
            countDownSubscription = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopCountdown()
    }
}