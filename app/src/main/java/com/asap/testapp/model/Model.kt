package com.asap.testapp.model

/**
 * Project TestApps
 * -------------------------------------
 * Created by Andrey Sapunov on 13/05/2018.
 * -------------------------------------
 */
class Model(val size : Int) {

    private val values = Array(size, { _ -> 0.0 })

    fun setValue(recordId : Int, value : Double) {
        values[recordId] = value
    }

    fun calculateSum() : Double {
        return values.sum()
    }

}