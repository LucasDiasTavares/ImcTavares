package com.example.tavares.imctavares.MVP_Resumo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.tavares.imctavares.MVP_Historico.HistoricoActivity
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import com.example.tavares.imctavares.MVP_PesoAltura.repositorios.Repo_imcT
import com.example.tavares.imctavares.R
import com.example.tavares.imctavares.Utils.formatToString
import kotlinx.android.synthetic.main.activity_resumo.*
import java.util.*

class ResumoActivity : AppCompatActivity() {

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

    }

    override fun onResume() {
        super.onResume()
        dados(Repo_imcT(this).getLastImct())
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

    private fun dados(dados: ImcT?){
        txt_peso_inicial.text = dados?.peso?.formatToString()?:"!"
        txt_resumo_Imc_result.text = dados?.imc?.formatToString()
        mudaCorIMC(dados?.imc?:0f)
    }


    private fun mudaCorIMC(IMC: Float){
        when{
            IMC < 16.99F -> {txt_resumo_Imc_result.setBackgroundColor(resources.getColor(R.color.colorLight_Blue))}
            IMC <= 18.49F -> {txt_resumo_Imc_result.setBackgroundColor(resources.getColor(R.color.colorGray))}
            IMC <= 24.99F -> {txt_resumo_Imc_result.setBackgroundColor(resources.getColor(R.color.colorGreenLight))}
            IMC <= 29.99F -> {txt_resumo_Imc_result.setBackgroundColor(resources.getColor(R.color.colorPrimary))}
            IMC <= 34.99F -> {txt_resumo_Imc_result.setBackgroundColor(resources.getColor(R.color.colorRedLight))}
            IMC <= 39.99F -> {txt_resumo_Imc_result.setBackgroundColor(resources.getColor(R.color.colorRed))}
            IMC > 40F -> {txt_resumo_Imc_result.setBackgroundColor(resources.getColor(R.color.colorDarkRed))}
        }
    }

}
