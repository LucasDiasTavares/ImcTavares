package com.example.tavares.imctavares.MVP_Historico

import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import java.util.*

interface HistoricoInterface {

    interface Model {
        fun criarImcT(imcT: ImcT)
        fun pegarTodosImcT(): ArrayList<ImcT>
        fun deletaImcT(id: Int)
        fun pegarUltimoImct(): ImcT
    }

    interface ViewImpl{
        fun mudaTask(tamanhoLista: Int)
        fun notifyAddItemHistorico(position : Int)
        fun notifyRemovedItemHistorico(position : Int)
    }

    interface Presenter {
        fun salvar(peso: Float, altura: Float)
        fun mostrarListaImcts(): ArrayList<ImcT>
        fun mostrarUltimoImctAdicionado() :ImcT
        fun deletarImct(id: Int)
        fun removeItemHistorico(posicaoAdapter: Int)
    }

}
