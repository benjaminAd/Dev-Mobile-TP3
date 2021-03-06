package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity2 : AppCompatActivity() {

    private lateinit var listeView: ListView
    private lateinit var datas: ArrayList<String>

    private var Nom: String = ""
    private var id: Int = 0

    private var RecupData: String = ""

    private val NomKey: String = "NOM"
    private val IDKey: String = "ID"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        listeView = findViewById<ListView>(R.id.ListView)
        val intent: Intent = intent

        if (!intent.equals(null)) {
            if (intent.hasExtra(NomKey)) Nom = intent.getStringExtra(NomKey).toString()
            if (intent.hasExtra(IDKey)) id = intent.getStringExtra(IDKey).toString().toInt()
            val file: FileInputStream = openFileInput(Nom.plus(id.toString()))
            val byteArray: ByteArray = ByteArray(1024)
            file.read(byteArray)
            RecupData = String(byteArray)
            val SplitDatas = RecupData.split(',')
            val datas = arrayOfNulls<String>(5)
            for (i in 0..3) {
                datas[i] = SplitDatas[i]
                println("Datas------${datas[i]}")
            }
            println("---------${Utilisation.NbUse}")
            datas[4] = Utilisation.NbUse.toString()
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas)
            listeView.adapter = adapter

        }
        lifecycle.addObserver(Utilisation())
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, R.string.onResume, Toast.LENGTH_SHORT).show()
    }

}