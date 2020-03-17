package com.yizisu.music.and.video

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
       val oldHash="8e496d133287434a933dcb63a5de8982"
        val hashLong=oldHash.toLong()
        val newHash=hashLong.toString(16)
        val newHash1=hashLong.toString()
        print(1)
    }
}
