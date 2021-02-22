package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class Inscription : AppCompatActivity() {

    private lateinit var EditNom: EditText
    private lateinit var EditPrenom: EditText
    private lateinit var EditAge: EditText
    private lateinit var EditNumTel: EditText
    private lateinit var EditPassword: EditText
    private lateinit var inscriptionButton: Button
    private lateinit var id: UUID

    private val NomKey: String = "NOM"
    private val PrenomKey: String = "PRENOM"
    private val AgeKey: String = "AGE"
    private val NumKey: String = "NUMTEL"
    private val IDKey: String = "ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inscription)
        EditNom = findViewById<EditText>(R.id.EditTextNom)
        EditPrenom = findViewById<EditText>(R.id.EditTextPrenom)
        EditAge = findViewById<EditText>(R.id.EditTextAge)
        EditNumTel = findViewById<EditText>(R.id.EditTextNumTel)
        EditPassword = findViewById<EditText>(R.id.EditTextPassword)
        inscriptionButton = findViewById<Button>(R.id.InscriptionButton)
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(NomKey)) EditNom.setText(savedInstanceState.get(NomKey).toString())
            if (savedInstanceState.containsKey(PrenomKey)) EditPrenom.setText(savedInstanceState.get(PrenomKey).toString())
            if (savedInstanceState.containsKey(AgeKey)) EditAge.setText(savedInstanceState.get(AgeKey).toString())
            if (savedInstanceState.containsKey(NumKey)) EditNumTel.setText(savedInstanceState.get(NumKey).toString())
            if (savedInstanceState.containsKey(IDKey)) id = StringToUUID(savedInstanceState.get(IDKey).toString())
        }
        inscriptionButton.setOnClickListener {
            if ((EditNom.text.isBlank()) || (EditPrenom.text.isBlank()) || (EditAge.text.isBlank()) || (EditNumTel.text.isBlank()) || (EditPassword.text.isBlank())) {
                Toast.makeText(this, R.string.notInscrit, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT).show()
                id = GenerateId()
            }
        }

    }

    fun GenerateId(): UUID {
        return UUID.randomUUID()
    }

    fun StringToUUID(chaine: String): UUID {
        return UUID.fromString(chaine)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NomKey, EditNom.text.toString())
        outState.putString(PrenomKey, EditPrenom.text.toString())
        outState.putString(AgeKey, EditAge.text.toString())
        outState.putString(NumKey, EditNumTel.text.toString())
        if (!id.toString().isBlank()) outState.putString(IDKey, id.toString())
        Toast.makeText(this, R.string.savedInstance, Toast.LENGTH_SHORT).show()
    }

}