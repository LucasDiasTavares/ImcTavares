package com.example.tavares.imctavares.MVP_Historico

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import com.example.tavares.imctavares.MVP_PesoAltura.repositorios.Repo_imcT
import com.example.tavares.imctavares.R
import com.example.tavares.imctavares.Utils.formatToString
import kotlinx.android.synthetic.main.activity_historico.*
import kotlinx.android.synthetic.main.add_imc_dialog_custom.*
import kotlinx.android.synthetic.main.add_imc_dialog_custom.view.*
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

class HistoricoActivity : AppCompatActivity(){
    private var adapterHistorico : HistoricoAdapter?=null
    private var listImcs = ArrayList<ImcT>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)
        initComponents()

        btn_adicionar?.setOnClickListener {
            showDialogImc()
        }

    }

    private fun initComponents(){

        //setting toolbar
        setSupportActionBar(toolbar)
        //home navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView.layoutManager = LinearLayoutManager(
                this, LinearLayout.VERTICAL, false)

        listImcs = Repo_imcT(this).getAllImcT()
        adapterHistorico = HistoricoAdapter(this, listImcs)
        recyclerView.adapter = adapterHistorico

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
            Toast.makeText(this,"Nada", Toast.LENGTH_SHORT).show()
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

    @SuppressLint("InflateParams")
    fun showDialogImc(
            context: Context = this) {

        var dialog: AlertDialog? = null
        val alert_builder = AlertDialog.Builder(context, R.style.CustomDialogTransParent)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.add_imc_dialog_custom, null)
        alert_builder.setView(dialogView)

        //dialogView.txt_title.text = title

        dialogView.dialog_first_button.setOnClickListener {
            salvar(dialogView.dialog_edit_peso.text.toString().toFloat(),
                    dialogView.dialog_edit_altura.text.toString().toFloat())
            dialog?.dismiss()
        }

        dialogView.dialog_second_button.setOnClickListener {
            dialog?.dismiss()
        }

        dialog = alert_builder.create()
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        try {
            if (!dialog?.isShowing!! && !(context as AppCompatActivity).isFinishing) dialog.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    private fun salvar(peso: Float, altura: Float): Int? {
        val imc = peso / (altura * altura)
        val imcT = ImcT(peso = peso, altura = altura, dataPesagem = Date(), imc =  imc.formatToString().toFloat())
        val retorno = Repo_imcT(this).createAtImcT(imcT)

        listImcs.add(imcT)
        adapterHistorico?.notifyItemInserted(listImcs.lastIndex)

        return retorno
    }

}

