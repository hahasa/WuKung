package io.mountx.wukung

import io.mountx.wukung.algorithm.permuteList
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AlgorithmUnitTest {

    @Test
    fun addition_isCorrect() {
        val output = ArrayList<ArrayList<Int>>()
        permuteList(arrayListOf(1), output = output)
        printList(output)
        output.clear()

        permuteList(arrayListOf(1, 2), output = output)
        printList(output)
        output.clear()

        permuteList(arrayListOf(1, 2, 3), output = output)
        printList(output)
        output.clear()

        permuteList(arrayListOf(1, 2, 3, 4), output = output)
        printList(output)
        output.clear()
    }
}
