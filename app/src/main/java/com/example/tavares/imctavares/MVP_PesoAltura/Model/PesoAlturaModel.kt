package com.example.tavares.imctavares.MVP_PesoAltura.Model

import android.content.Context
import com.example.tavares.imctavares.MVP_PesoAltura.PesoAlturaInterface
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import com.example.tavares.imctavares.MVP_PesoAltura.repositorios.Repo_imcT


class PesoAlturaModel(private val context: Context): PesoAlturaInterface.Model {

    override fun criarImcT(imcT: ImcT) {
        Repo_imcT(context).createAtImcT(imcT)
    }

}