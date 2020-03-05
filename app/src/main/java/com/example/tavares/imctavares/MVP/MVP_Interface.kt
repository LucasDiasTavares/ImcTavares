package com.example.tavares.imctavares.MVP

import android.content.Context
import android.view.MenuItem
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import java.util.ArrayList

interface MVP_Interface {

    interface ModelImpl {
        fun createAtImcT(imcT: ImcT): Int?
        fun deleteImcT(id: Int)
    }

    interface ViewImpl {
        //PesoAlturaAcivity
        fun salvar(): Int?
        //ResumoActivity
        fun mudaCorIMC(IMC: Float)
        fun dados(dados: ImcT?)
        //HistoricoActivity
        fun salvar(peso: Float, altura: Float): Int?
    }

    interface PresenterImpl {
        fun getContext(): Context
        fun get2(): Boolean
        fun getAllImcT(): ArrayList<ImcT>
        fun getLastImct() : ImcT?
    }

}