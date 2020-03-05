package com.example.tavares.imctavares

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.tavares.imctavares.MVP_PesoAltura.View.PesoAlturaActivity
import com.example.tavares.imctavares.MVP_PesoAltura.repositorios.Repo_imcT
import com.example.tavares.imctavares.MVP_Resumo.ResumoActivity

class SplashScreenActivity : AppCompatActivity() {


    private var DelayHandler: Handler? = null
    private val SPLASHDELAY: Long = 1500 //1.5 seconds

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            if(Repo_imcT(this).get2()){
                val intent = Intent(applicationContext, ResumoActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(applicationContext, PesoAlturaActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //Initialize the Handler
        DelayHandler = Handler()

        //Navigate with delay
        DelayHandler!!.postDelayed(mRunnable, SPLASHDELAY)
    }

    public override fun onDestroy() {

        if (DelayHandler != null) {
            DelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

}
