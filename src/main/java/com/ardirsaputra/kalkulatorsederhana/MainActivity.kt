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
        tombolSatu.setOnClickListener { appendOnExpresstion("1", true) }
        tombolDua.setOnClickListener { appendOnExpresstion("2", true) }
        tombolTiga.setOnClickListener { appendOnExpresstion("3", true) }
        tombolEmpat.setOnClickListener { appendOnExpresstion("4", true) }
        tombolLima.setOnClickListener { appendOnExpresstion("5", true) }
        tombolEnam.setOnClickListener { appendOnExpresstion("6", true) }
        tombolTujuh.setOnClickListener { appendOnExpresstion("7", true) }
        tombolDelapan.setOnClickListener { appendOnExpresstion("8", true) }
        tombolSembilan.setOnClickListener { appendOnExpresstion("9", true) }
        tombolNol.setOnClickListener { appendOnExpresstion("0", true) }
        tombolTitik.setOnClickListener { appendOnExpresstion(".", true) }

        //Operators
        tombolTambah.setOnClickListener { appendOnExpresstion("+", false) }
        tombolKurang.setOnClickListener { appendOnExpresstion("-", false) }
        tombolKali.setOnClickListener { appendOnExpresstion("*", false) }
        tombolBagi.setOnClickListener { appendOnExpresstion("/", false) }
        tombolBukaKurung.setOnClickListener { appendOnExpresstion("(", false) }
        tombolTutupKurung.setOnClickListener { appendOnExpresstion(")", false) }

        tombolBersihikan.setOnClickListener {
            layarEkspresiMatematika.text = ""
            layarHasil.text = ""
        }

        tombolHapus.setOnClickListener {
            val string = layarEkspresiMatematika.text.toString()
            if(string.isNotEmpty()){
                layarEkspresiMatematika.text = string.substring(0,string.length-1)
            }
            layarHasil.text = ""
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
            }
        }

    }

    fun appendOnExpresstion(string: String, canClear: Boolean) {
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
