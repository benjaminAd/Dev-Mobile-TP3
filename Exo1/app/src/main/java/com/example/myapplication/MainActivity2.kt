package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream

class MainActivity2 : AppCompatActivity() {

    private lateinit var listeView: ListView

    private var Nom: String = ""
    private var id: Int = 0

    private var RecupData: String = ""

    private val NomKey: String = "NOM"
    private val IDKey: String = "ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        listeView = findViewById<ListView>(R.id.ListView)
        val intent: Intent = getIntent()
        if (intent != null) {
            if (intent.hasExtra(NomKey)) Nom = intent.getStringExtra(NomKey).toString()
            if (intent.hasExtra(IDKey)) id = intent.getStringExtra(IDKey).toString().toInt()
            val file: FileInputStream = openFileInput(Nom.plus(id.toString()))
            var byteArray: ByteArray = ByteArray(1024)
            file.read(byteArray)
            RecupData = String(byteArray)
            val SplitDatas = RecupData.split(',')
            val datas = arrayOfNulls<String>(SplitDatas.size)
            for (i in SplitDatas.indices) {
                datas[i] = SplitDatas.get(i)
                println("-------$i----- ${datas[i]}")
            }
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas)
            listeView.adapter = adapter

        }

    }
}