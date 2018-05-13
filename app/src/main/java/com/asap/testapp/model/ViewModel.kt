package com.asap.testapp.model

import java.util.*

/**
 * Project TestApps
 * -------------------------------------
 * Created by Andrey Sapunov on 13/05/2018.
 * -------------------------------------
 */

class ViewModel(val size : Int) : Observable() {

    private val model = Model(size)

    fun setValue(recordId : Int, value : Double) {
        if (recordId >= size) {
            // Avoid exceptions

            return
        }

        model.setValue(recordId, value)

        setChanged()
        notifyObservers(model.calculateSum())
    }

}