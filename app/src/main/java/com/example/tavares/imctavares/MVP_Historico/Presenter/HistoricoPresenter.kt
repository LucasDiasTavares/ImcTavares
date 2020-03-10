package com.example.tavares.imctavares.MVP_Historico.Presenter

import android.content.Context
import com.example.tavares.imctavares.MVP_Historico.HistoricoInterface
import com.example.tavares.imctavares.MVP_Historico.Model.HistoricoModel
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import com.example.tavares.imctavares.Utils.formatToString
import java.util.*
import kotlin.collections.ArrayList

class HistoricoPresenter(private val context: Context,
                         private val view: HistoricoInterface.ViewImpl): HistoricoInterface.Presenter {

    private var model: HistoricoInterface.Model = HistoricoModel(context)
    private var listImcT : ArrayList<ImcT> = ArrayList()

    override fun mostrarListaImcts(): ArrayList<ImcT> {
        listImcT.addAll(model.pegarTodosImcT())
        return listImcT
    }

    override fun mostrarUltimoImctAdicionado() :ImcT{
        return model.pegarUltimoImct()
    }

    override fun deletarImct(id: Int) {
        return model.deletaImcT(id)
    }

    override fun salvar(peso: Float, altura: Float) {
        val imc = (peso / (altura * altura)).formatToString().toFloat()
        val retorno = ImcT(
                peso = peso, altura =  altura, dataPesagem =  Date(), imc =  imc)

        model.criarImcT(retorno)
        listImcT.add(mostrarUltimoImctAdicionado())
        view.notifyAddItemHistorico(listImcT.lastIndex)
    }

    override fun removeItemHistorico(posicaoAdapter: Int) {
        val id = listImcT[posicaoAdapter].id
        model.deletaImcT(id!!)
        listImcT.removeAt(posicaoAdapter)
        view.notifyRemovedItemHistorico(posicaoAdapter)
    }
}