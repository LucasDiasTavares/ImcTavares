package com.example.tavares.imctavares.MVP_Historico

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import com.example.tavares.imctavares.MVP_PesoAltura.repositorios.Repo_imcT
import com.example.tavares.imctavares.R
import kotlinx.android.synthetic.main.linha_historico.view.*
import java.text.SimpleDateFormat
import com.example.tavares.imctavares.MVP_PesoAltura.View.PesoAlturaActivity


class HistoricoAdapter(
        private var context: Context,
        private var list: ArrayList<ImcT> ) :RecyclerView.Adapter<HistoricoAdapter.MyViewHolder>() {

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

        override fun onClick(view: View?) {
            when (view?.id) {
                R.id.btn_historico_delete -> {
                    removeItemHistorico(adapterPosition)
                    mudaTask(list.size)
                }
            }
        }


        private fun removeItemHistorico(position: Int) {
            val id = list[position].id
            Repo_imcT(context).deleteImcT(id ?: -1)
            list.removeAt(position)
            Toast.makeText(context, "Deletado", Toast.LENGTH_SHORT).show()
            notifyItemRemoved(position)
        }


        private fun mudaTask(tamanhoLista: Int) {
            if(tamanhoLista <= 0) {
                    val intent = Intent(context, PesoAlturaActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
        }


    }

}