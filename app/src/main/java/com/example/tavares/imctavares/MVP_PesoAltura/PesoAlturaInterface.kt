package com.example.tavares.imctavares.MVP_PesoAltura

import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT

interface PesoAlturaInterface {

    interface Model {
        fun criarImcT(imcT: ImcT)
    }

    interface View {
        //PesoAlturaAcivity
        fun initView()

    }

    interface Presenter {
        fun salvar(peso: Float, altura: Float)
    }

}
