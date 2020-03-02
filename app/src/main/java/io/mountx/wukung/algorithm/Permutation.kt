package io.mountx.wukung.algorithm

import java.util.*
import kotlin.collections.ArrayList

/**
 * @author hhs
 * Created on 2020-02-24
 */

fun permuteList(args: List<Int>, index: Int = 0, output: ArrayList<ArrayList<Int>>? = null) {
    if (index >= args.size - 1) {
        if (output != null) {
            output.add(ArrayList<Int>().apply {
                args.forEach {
                    add(it)
                }
            })
        } else {
            args.forEach {
                print(it)
            }
            println()
        }
        return
    }
    permuteList(args, index + 1, output)
    for (i in index + 1 until args.size) {
        Collections.swap(args, index, i)
        permuteList(args, index + 1, output)
        Collections.swap(args, i, index)
    }
}