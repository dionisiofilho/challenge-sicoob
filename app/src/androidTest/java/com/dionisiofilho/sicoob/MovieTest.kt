package com.dionisiofilho.sicoob

import junit.framework.TestCase
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class MovieTest : TestCase() {

    @Test
    fun testDataValid() {
        val formato = SimpleDateFormat("yyyy-MM-dd", Locale("en", "US"))
        formato.isLenient = false

        try {
            formato.parse("2019-02-28")
        } catch (e: Exception) {
            assertTrue(false)
        }
        assertTrue(true)
    }

    @Test
    fun testDataNoValid() {
        val formato = SimpleDateFormat("yyyy-MM-dd", Locale("en", "US"))
        formato.isLenient = false

        try {
            formato.parse("2019-02-31")
        } catch (e: Exception) {
            assertTrue(false)
        }
        assertTrue(true)
    }

}