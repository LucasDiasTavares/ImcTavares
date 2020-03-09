package com.example.tavares.imctavares.MVP_Historico.View

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import com.example.tavares.imctavares.MVP.fragmentActivity
import com.example.tavares.imctavares.MVP_Historico.HistoricoAdapter
import com.example.tavares.imctavares.MVP_Historico.HistoricoInterface.*
import com.example.tavares.imctavares.MVP_Historico.Presenter.HistoricoPresenter
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import com.example.tavares.imctavares.R
import kotlinx.android.synthetic.main.activity_historico.*
import kotlinx.android.synthetic.main.add_imc_dialog_custom.view.*
import kotlin.collections.ArrayList

class HistoricoActivity : AppCompatActivity(), View {

    private var presenter: HistoricoPresenter? = null

    private var adapterHistorico : HistoricoAdapter?=null
    private var listImcs = ArrayList<ImcT>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)
        presenter = HistoricoPresenter(this)
        initComponents()

    }

    private fun initComponents(){
        //setting toolbar
        setSupportActionBar(toolbar)
        //home navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_fragment?.setOnClickListener{
            // start your next activity, FRAGMENT
            val intent = Intent(this, fragmentActivity::class.java)
            startActivity(intent)

        }

        btn_adicionar?.setOnClickListener {
            showDialogImc()
        }
        recyclerView.layoutManager = LinearLayoutManager(
                this, LinearLayout.VERTICAL, false)

        val todosImcT = presenter?.mostrarListaImcts()
        listImcs = todosImcT!!
        adapterHistorico = presenter?.let { HistoricoAdapter(this, listImcs, presenter = it) }
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
            val peso = dialogView.dialog_edit_peso.text.toString().toFloat()
            val altura = dialogView.dialog_edit_altura.text.toString().toFloat()
            presenter?.salvar(peso, altura)
            listImcs.add(presenter?.mostrarUltimoImctAdicionado()!!)
            adapterHistorico?.notifyItemInserted(listImcs.lastIndex)
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
}

