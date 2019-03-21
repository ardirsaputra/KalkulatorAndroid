package com.ardirsaputra.kalkulatorsederhana

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        layarEkspresiMatematika.text= "0" ;
        tombolSatu.setOnClickListener { masukanKeEkspresiMatematika("1", true) }
        tombolDua.setOnClickListener { masukanKeEkspresiMatematika("2", true) }
        tombolTiga.setOnClickListener { masukanKeEkspresiMatematika("3", true) }
        tombolEmpat.setOnClickListener { masukanKeEkspresiMatematika("4", true) }
        tombolLima.setOnClickListener { masukanKeEkspresiMatematika("5", true) }
        tombolEnam.setOnClickListener { masukanKeEkspresiMatematika("6", true) }
        tombolTujuh.setOnClickListener { masukanKeEkspresiMatematika("7", true) }
        tombolDelapan.setOnClickListener { masukanKeEkspresiMatematika("8", true) }
        tombolSembilan.setOnClickListener { masukanKeEkspresiMatematika("9", true) }
        tombolNol.setOnClickListener { masukanKeEkspresiMatematika("0", true) }
        tombolTitik.setOnClickListener { masukanKeEkspresiMatematika(".", true) }

        //Operators
        tombolTambah.setOnClickListener { masukanKeEkspresiMatematika("+", false) }
        tombolKurang.setOnClickListener { masukanKeEkspresiMatematika("-", false) }
        tombolKali.setOnClickListener { masukanKeEkspresiMatematika("*", false) }
        tombolBagi.setOnClickListener { masukanKeEkspresiMatematika("/", false) }
        tombolBukaKurung.setOnClickListener { masukanKeEkspresiMatematika("(", false) }
        tombolTutupKurung.setOnClickListener { masukanKeEkspresiMatematika(")", false) }

        tombolBersihikan.setOnClickListener {
            layarEkspresiMatematika.text = "0"
            layarHasil.text = ""
        }

        tombolHapus.setOnClickListener {
            val string = layarEkspresiMatematika.text.toString()
            if(string.isNotEmpty()){
                layarEkspresiMatematika.text = string.substring(0,string.length-1)
            }
            layarHasil.text = ""
            isZero()
        }

        tombolSamaDengan.setOnClickListener {
            try {

                val expression = ExpressionBuilder(layarEkspresiMatematika.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble())
                    layarHasil.text = longResult.toString()
                else
                    layarHasil.text = result.toString()

            }catch (e:Exception){
                Log.d("Exception"," message : " + e.message )
                layarHasil.text = "Ekspresi Error"
            }
        }
    }
    fun isZero(){
        if(layarEkspresiMatematika.text.toString() == ""){
            layarEkspresiMatematika.text = "0";
        }
    }
    fun masukanKeEkspresiMatematika(string: String, canClear: Boolean) {
        if(layarEkspresiMatematika.text.toString() == "0") {
            if(!canClear){

            }else{
                if(string == "." || string == "0" ){

                }else{
                    layarEkspresiMatematika.text = layarEkspresiMatematika.text.toString().substring(0,layarEkspresiMatematika.text.toString().length-1)
                }
            }
        }
        if(layarHasil.text.isNotEmpty()){
            layarEkspresiMatematika.text = ""
        }
        if (canClear) {
            layarHasil.text = ""
            layarEkspresiMatematika.append(string)
        } else {
            layarEkspresiMatematika.append(layarHasil.text)
            layarEkspresiMatematika.append(string)
            layarHasil.text = ""
        }
    }
}
