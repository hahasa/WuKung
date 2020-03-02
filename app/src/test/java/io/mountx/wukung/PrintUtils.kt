package io.mountx.wukung

/**
 * @author hhs
 * Created on 2020-02-25
 */

fun printList(list: ArrayList<ArrayList<Int>>) {
    print("total count:${list.size}")
    println()
    list.forEach {
        it.forEach { item ->
            print(item)
        }
        println()
    }
    println()
    println()
}
