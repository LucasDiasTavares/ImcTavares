package com.example.tavares.imctavares.MVP_Historico

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import com.example.tavares.imctavares.R
import kotlinx.android.synthetic.main.linha_historico.view.*
import java.text.SimpleDateFormat
import com.example.tavares.imctavares.Utils.HackListener


class HistoricoAdapter(
        private var context: Context,
        private var list: ArrayList<ImcT>,
        var presenter: HistoricoInterface.Presenter,
        private var hack : HackListener) :RecyclerView.Adapter<HistoricoAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(root: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.linha_historico, root, false)
        return MyViewHolder(view)
    }


    override fun getItemCount() = list.size


    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        val df = SimpleDateFormat("dd/MM/yyyy")

        viewHolder.itemView.txt_historico_peso.text = list[position].peso.toString()
        viewHolder.itemView.txt_historico_imc.text = list[position].imc.toString()
        viewHolder.itemView.txt_historico_dataPesagem.text = df.format(list[position].dataPesagem)

    }


    inner class MyViewHolder(itemViewHolder: View) : RecyclerView.ViewHolder(itemViewHolder),View.OnClickListener {
        init {
            itemViewHolder.btn_historico_delete?.setOnClickListener(this)
        }
        override fun onClick(view: View) {
            hack.onClickHack(view, adapterPosition)
        }
    }
}