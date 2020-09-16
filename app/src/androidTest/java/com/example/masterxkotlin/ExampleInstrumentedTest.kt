package com.example.masterxkotlin

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented left_and_right, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under left_and_right.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.masterxkotlin", appContext.packageName)
    }
}