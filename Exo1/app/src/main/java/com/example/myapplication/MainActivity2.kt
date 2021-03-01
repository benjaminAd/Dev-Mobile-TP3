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
    private lateinit var use: Utilisation

    private var Nom: String = ""
    private var id: Int = 0

    private var RecupData: String = ""

    private val NomKey: String = "NOM"
    private val IDKey: String = "ID"
    private val NbUseKey: String = "NBUSE"

    private var NbUse: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        listeView = findViewById<ListView>(R.id.ListView)
        val intent: Intent = intent

        if (!intent.equals(null)) {
            if (intent.hasExtra(NomKey)) Nom = intent.getStringExtra(NomKey).toString()
            if (intent.hasExtra(IDKey)) id = intent.getStringExtra(IDKey).toString().toInt()
            NbUse =
                if ((savedInstanceState != null) && (savedInstanceState.containsKey(NbUseKey))) {
                    savedInstanceState.getInt(
                        NbUseKey
                    )
                } else {
                    if (intent.hasExtra(NbUseKey)) intent.getStringExtra(NbUseKey).toInt() else 0
                }
            println("-----NbUse=$NbUse")
            use = Utilisation(NbUse)
            val file: FileInputStream = openFileInput(Nom.plus(id.toString()))
            val byteArray: ByteArray = ByteArray(1024)
            file.read(byteArray)
            RecupData = String(byteArray)
            val SplitDatas = RecupData.split(',')
            val datas = arrayOfNulls<String>(6)
            for (i in 0..4) {
                datas[i] = SplitDatas[i]
            }
            println("---------${use.getNbUse()}")
            datas[5] = use.getNbUse().toString()
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas)
            listeView.adapter = adapter

        }
        lifecycle.addObserver(use)
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, R.string.onResume, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt(NbUseKey, use.getNbUse())
    }

}