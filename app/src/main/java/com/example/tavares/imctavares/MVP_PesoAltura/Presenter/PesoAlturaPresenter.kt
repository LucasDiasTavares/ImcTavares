package com.example.tavares.imctavares.MVP_PesoAltura.Presenter
import android.content.Context
import com.example.tavares.imctavares.MVP_PesoAltura.Model.PesoAlturaModel
import com.example.tavares.imctavares.MVP_PesoAltura.PesoAlturaInterface.*
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import com.example.tavares.imctavares.Utils.formatToString
import java.util.*

class PesoAlturaPresenter(_view: View, context: Context): Presenter{


    private var view: View = _view
    private var model: PesoAlturaModel = PesoAlturaModel(context)

    init {
        view.initView()
    }

    override fun salvar(peso: Float, altura: Float) {
        val imc = (peso / (altura * altura)).formatToString().toFloat()
        val retorno = ImcT(
                peso = peso, altura =  altura, dataPesagem =  Date(), imc =  imc)
        model.criarImcT(retorno)
    }
}

