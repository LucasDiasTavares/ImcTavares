package com.example.tavares.imctavares.MVP_Resumo.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.tavares.imctavares.MVP_Historico.View.HistoricoActivity
import com.example.tavares.imctavares.MVP_Resumo.Presenter.ResumoPresenter
import com.example.tavares.imctavares.MVP_Resumo.ResumoInterface
import com.example.tavares.imctavares.R
import com.example.tavares.imctavares.Utils.formatToString
import kotlinx.android.synthetic.main.activity_resumo.*

@Suppress("DEPRECATION")
class ResumoActivity : AppCompatActivity(), ResumoInterface.View {

    private  var presenter: ResumoPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumo)

        //setting toolbar
        setSupportActionBar(toolbar)
        //home navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        ver_historico.setOnClickListener{
            // start your next activity
            val intent = Intent(this, HistoricoActivity::class.java)
            startActivity(intent)
        }
        presenter = ResumoPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        txt_resumo_Imc_result.text = presenter?.processaUltimoImct()?.imc?.formatToString()
        txt_peso_inicial.text = presenter?.processaUltimoImct()?.peso?.formatToString()
        changeColorTxtBackground()
    }

    override fun changeColorTxtBackground() {
        val imc = presenter?.processaUltimoImct()?.imc
        when{
            imc!! < 16.99F -> {txt_resumo_Imc_result.setBackgroundColor(resources.getColor(R.color.colorLight_Blue))}
            imc <= 18.49F -> {txt_resumo_Imc_result.setBackgroundColor(resources.getColor(R.color.colorGray))}
            imc <= 24.99F -> {txt_resumo_Imc_result.setBackgroundColor(resources.getColor(R.color.colorGreenLight))}
            imc <= 29.99F -> {txt_resumo_Imc_result.setBackgroundColor(resources.getColor(R.color.colorPrimary))}
            imc <= 34.99F -> {txt_resumo_Imc_result.setBackgroundColor(resources.getColor(R.color.colorRedLight))}
            imc <= 39.99F -> {txt_resumo_Imc_result.setBackgroundColor(resources.getColor(R.color.colorRed))}
            imc > 40F -> {txt_resumo_Imc_result.setBackgroundColor(resources.getColor(R.color.colorDarkRed))}
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
            //Rounded android
            Toast.makeText(this,"You are been blessed",Toast.LENGTH_SHORT).show()
            true
        }
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
