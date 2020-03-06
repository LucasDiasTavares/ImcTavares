package com.example.tavares.imctavares.MVP_Historico.Model

import android.content.Context
import com.example.tavares.imctavares.MVP_Historico.HistoricoInterface.*
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import com.example.tavares.imctavares.MVP_PesoAltura.repositorios.Repo_imcT
import java.util.ArrayList

class HistoricoModel(private val context: Context) : Model{

    override fun pegarTodosImcT(): ArrayList<ImcT> {
        return Repo_imcT(context).getAllImcT()
    }

    override fun criarImcT(imcT: ImcT) {
        Repo_imcT(context).createAtImcT(imcT)
    }

    override fun deletaImcT(id: Int) {
        Repo_imcT(context).deleteImcT(id)
    }

    override fun pegarUltimoImct(): ImcT {
        return Repo_imcT(context).getLastImct()
    }
}