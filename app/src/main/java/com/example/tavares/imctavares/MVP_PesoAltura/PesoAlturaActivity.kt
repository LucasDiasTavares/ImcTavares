package com.example.tavares.imctavares.MVP_PesoAltura

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.tavares.imctavares.MVP.MVP_Interface
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import com.example.tavares.imctavares.MVP_PesoAltura.repositorios.Repo_imcT
import com.example.tavares.imctavares.MVP_Resumo.ResumoActivity
import com.example.tavares.imctavares.R
import com.example.tavares.imctavares.Utils.formatToString
import kotlinx.android.synthetic.main.activity_peso_altura.*
import kotlinx.android.synthetic.main.linha_historico.*
import java.text.DecimalFormat
import java.util.*

class PesoAlturaActivity : AppCompatActivity(), MVP_Interface{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peso_altura)

        //setting toolbar
        setSupportActionBar(toolbar)
        //home navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        salvar.setOnClickListener {
            val idRetorno = salvar()

            // start your next activity
            val intent = Intent(
                    this, ResumoActivity::class.java).putExtra("id", idRetorno)
            startActivity(intent)
        }
    }


        fun salvar(): Int? {
        val peso = edit_peso.text.toString().toFloat()
        val altura = edit_altura.text.toString().toFloat()
        val imc = peso / (altura * altura)
        val retorno = Repo_imcT(this).createAtImcT(
                ImcT(peso = peso, altura =  altura, dataPesagem =  Date(), imc =  imc.formatToString().toFloat()))
        return retorno
    }


    //setting menu in action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_print -> {
            finish()
            true
        }

        android.R.id.home ->{
            //Back icon
            Toast.makeText(this,"Home",Toast.LENGTH_LONG).show()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }



}

