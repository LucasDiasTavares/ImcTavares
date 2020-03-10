package com.example.tavares.imctavares.MVP

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.tavares.imctavares.R
import kotlinx.android.synthetic.main.activity_historico.*

class fragmentActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    //Initialized my two fragments
    private val firstFragment = FirstFragment()
    private val secondFragment = SecondFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        initComponents()

    }

    private fun initComponents(){
        //setting toolbar
        setSupportActionBar(toolbar)
        //home navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

    //Do not remove the v: View
    fun btnOne(v: View){
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.myFragment, firstFragment)
        fragmentTransaction.commit()
    }

    //Do not remove the v: View
    fun btnTwo(v:View){
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.myFragment, secondFragment)
        fragmentTransaction.commit()
    }

    //setting menu in action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home ->{
            //Back icon
            finish()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}
