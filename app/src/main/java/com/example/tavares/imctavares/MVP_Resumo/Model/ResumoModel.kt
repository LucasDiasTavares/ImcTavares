package com.example.tavares.imctavares.MVP_Resumo.Model

import android.content.Context
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import com.example.tavares.imctavares.MVP_PesoAltura.repositorios.Repo_imcT
import com.example.tavares.imctavares.MVP_Resumo.ResumoInterface

class ResumoModel(private val context: Context): ResumoInterface.Model {
    override fun getUltimoImct(): ImcT {
        return Repo_imcT(context).getLastImct()
    }
}