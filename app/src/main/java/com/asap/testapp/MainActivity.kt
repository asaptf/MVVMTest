package com.asap.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.asap.testapp.model.ViewModel
import com.asap.testapp.utils.afterTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity(), Observer {

    private val viewModel = ViewModel(6)

    init {
        viewModel.addObserver(this)
    }

    private fun bindTextChangedListener(edit : EditText, recordId : Int) {

        edit.afterTextChanged { editable ->
            val value = editable.toDouble()
            viewModel.setValue(recordId, value)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind listeners

        bindTextChangedListener(edit0, 0)
        bindTextChangedListener(edit1, 1)
        bindTextChangedListener(edit2, 2)
        bindTextChangedListener(edit3, 3)
        bindTextChangedListener(edit4, 4)
        bindTextChangedListener(edit5, 5)

        // Bind events on total field

        editTotal.keyListener = null
        editTotal.setOnFocusChangeListener { _, isActive -> if (isActive) onFieldTotalTap() }
        editTotal.setOnClickListener { onFieldTotalTap() }
    }

    override fun update(p0: Observable?, p1: Any?) {
        if (p1 is Double) {
            if (totalTextHidden.isEmpty()) {
                editTotal.setText(p1.toString())
            } else {
                totalTextHidden = p1.toString()
            }
        }

    }

    // Related to total field blinking

    private var blinkTimer : Timer? = null
    private var totalTextHidden = ""

    private fun onFieldTotalTap() {
        if (blinkTimer == null) {

            blinkTimer = Timer()
            blinkTimer!!.schedule( timerTask {

                runOnUiThread({
                    if (totalTextHidden.isEmpty()) {
                        totalTextHidden = editTotal.text.toString()
                        editTotal.setText("")
                    } else {
                        editTotal.setText(totalTextHidden)
                        totalTextHidden = ""
                    }
                })

            }, 0,500)

        } else {
            blinkTimer!!.cancel()
            blinkTimer = null

            if (totalTextHidden.isNotEmpty()) {
                editTotal.setText(totalTextHidden)
                totalTextHidden = ""
            }
        }
    }


}
