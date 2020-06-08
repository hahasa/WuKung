package io.mountx.common.app

import androidx.annotation.LayoutRes

/**
 * @author Ha Sang
 * Created on 2020/4/3
 */
open class MxFragment : LogFragment {

    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)
}