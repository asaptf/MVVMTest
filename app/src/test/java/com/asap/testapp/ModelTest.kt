package com.asap.testapp

import com.asap.testapp.model.Model
import org.junit.Test
import org.junit.Assert.*

class ModelTest {

    @Test
    fun testModelCalculation() {
        val m = Model(3)

        m.setValue(0, 5.0)
        m.setValue(1, 4.5)
        m.setValue(2, 4.5)

        assertEquals(14.0, m.calculateSum(), 0.0)

        m.setValue(1, 9.0)
        m.setValue(1, 8.0)

        assertEquals(17.5, m.calculateSum(), 0.0)

        m.setValue(2, -0.5)

        assertEquals(12.5, m.calculateSum(), 0.0)
    }

    @Test
    fun testModelRange() {
        val m = Model(1)

        m.setValue(0, 10.0)

        try {
            m.setValue(1, 10.0)
        } catch (e : ArrayIndexOutOfBoundsException) {
            // Success
        }

    }


}
