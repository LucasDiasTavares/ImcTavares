package com.example.tavares.imctavares.MVP_Resumo.Presenter

import android.content.Context
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import com.example.tavares.imctavares.MVP_Resumo.Model.ResumoModel
import com.example.tavares.imctavares.MVP_Resumo.ResumoInterface

class ResumoPresenter(context: Context):ResumoInterface.Presenter {

    private var model: ResumoModel = ResumoModel(context)

    override fun processaUltimoImct(): ImcT {
        return  model.getUltimoImct()
    }
}