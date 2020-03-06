package com.example.tavares.imctavares.MVP_Historico.Presenter

import android.content.Context
import com.example.tavares.imctavares.MVP_Historico.Model.HistoricoModel
import com.example.tavares.imctavares.MVP_Historico.HistoricoInterface.*
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import com.example.tavares.imctavares.Utils.formatToString
import java.util.*

class HistoricoPresenter(context: Context): Presenter {

    private var model: Model = HistoricoModel(context)


    override fun mostrarListaImcts(): ArrayList<ImcT> {
        return model.pegarTodosImcT()
    }

    override fun salvar(peso: Float, altura: Float) {
        val imc = (peso / (altura * altura)).formatToString().toFloat()
        val retorno = ImcT(
                peso = peso, altura =  altura, dataPesagem =  Date(), imc =  imc)
        model.criarImcT(retorno)
    }

    override fun mostrarUltimoImctAdicionado() :ImcT{
        return model.pegarUltimoImct()
    }

    override fun deletarImct(id: Int) {
        return model.deletaImcT(id)
    }

}