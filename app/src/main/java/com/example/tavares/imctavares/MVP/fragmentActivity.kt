package com.example.tavares.imctavares.MVP

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tavares.imctavares.R

class fragmentActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    //Initialized my two fragments
    private val firstFragment = FirstFragment()
    private val secondFragment = SecondFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        //Display First Fragment initially
        val fragmentTransaction = fragmentManager.beginTransaction()
        /*In the fragmentTransaction.replace() method,
        the First parameter is the ViewGroup
        where we want the fragment to be displayed and
        the Second parameter is which fragment to be displayed.*/
        fragmentTransaction.replace(R.id.myFragment, firstFragment)
        //You must call commit() for the changes to take effect.
        fragmentTransaction.commit()

    }

    fun btnOne(v: View){
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.myFragment, firstFragment)
        fragmentTransaction.commit()
    }

    fun btnTwo(v:View){
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.myFragment, secondFragment)
        fragmentTransaction.commit()
    }

}
