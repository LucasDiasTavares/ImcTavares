package com.example.tavares.imctavares.MVP_PesoAltura.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.tavares.imctavares.MVP_PesoAltura.PesoAlturaInterface
import com.example.tavares.imctavares.MVP_PesoAltura.Presenter.PesoAlturaPresenter
import com.example.tavares.imctavares.MVP_Resumo.ResumoActivity
import com.example.tavares.imctavares.R
import kotlinx.android.synthetic.main.activity_peso_altura.*

class PesoAlturaActivity : AppCompatActivity(), PesoAlturaInterface.View {
    private var presenter: PesoAlturaPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peso_altura)

        //setting toolbar
        setSupportActionBar(toolbar)
        //home navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        presenter = PesoAlturaPresenter(this, this)

    }

    override fun initView() {
        salvar.setOnClickListener {
            val peso = edit_peso.text.toString().toFloat()
            val altura = edit_altura.text.toString().toFloat()
            presenter?.salvar(peso, altura)
            //val idRetorno = salvar()
            val intent = Intent(
                    this, ResumoActivity::class.java)//.putExtra("id", idRetorno)
            startActivity(intent)
        }
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

