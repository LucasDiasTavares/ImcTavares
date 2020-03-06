package com.example.tavares.imctavares.MVP_Resumo

import android.widget.TextView
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT

interface ResumoInterface {

    interface Model {
        fun getUltimoImct() : ImcT
    }

    interface View {
        fun changeColorTxtBackground()
    }
    interface Presenter {
        //ResumoAcivity
        fun processaUltimoImct() :ImcT
    }


}