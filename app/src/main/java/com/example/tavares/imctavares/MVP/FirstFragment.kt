package com.example.tavares.imctavares.MVP

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.tavares.imctavares.R
import kotlinx.android.synthetic.main.first_fragment.*

class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        first_fragment_text.text = "1st Fragment"
        checkbox.setOnClickListener {
            if (checkbox.isChecked) {checkbox.text = "Sim"} else checkbox.text = "Não"
        }
        initComponents()
        btn_get_values.setOnClickListener{
            getValues(view)
        }
    }

    private fun initComponents(){

        // Create an ArrayAdapter
        val adapter = ArrayAdapter.createFromResource(context,
                R.array.city_list, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinner.adapter = adapter
    }

    fun getValues(view: View) {
        Toast.makeText(context, "" + spinner.selectedItem.toString(), Toast.LENGTH_LONG).show()
    }


}

