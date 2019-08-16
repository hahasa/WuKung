package io.mountx.wukung.drawable

import android.os.Bundle
import android.view.View
import io.mountx.common.app.LogFragment
import io.mountx.wukung.R
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_countdown_progressbar.*
import java.util.concurrent.TimeUnit

/**
 * @author Ha Sang
 * Created on 2019-07-31
 */

private const val COUNTDOWN_SECOND = 100

class CountdownProgressbarFragment : LogFragment(R.layout.fragment_countdown_progressbar) {

    private var restSecond = 0
    private var countDownDisposable: Disposable? = null

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
        if (restSecond > 0 && countDownDisposable == null) {
            updateCountdownProgressView()
            countDownDisposable = Flowable
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
        countDownDisposable?.takeUnless { it.isDisposed }?.dispose()
        countDownDisposable = null
    }

    override fun onDestroy() {
        super.onDestroy()
        stopCountdown()
    }
}