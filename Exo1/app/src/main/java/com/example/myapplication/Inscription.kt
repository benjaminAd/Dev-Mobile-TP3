package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import java.io.FileOutputStream
import androidx.lifecycle.LifecycleObserver as LifecycleObserver

class Inscription : AppCompatActivity() {

    private lateinit var EditNom: EditText
    private lateinit var EditPrenom: EditText
    private lateinit var EditAge: EditText
    private lateinit var EditNumTel: EditText
    private lateinit var EditPassword: EditText
    private lateinit var inscriptionButton: Button
    private lateinit var SubmitButton: Button
    private lateinit var PlanningButton: Button
    private var id: Int = 0
    private lateinit var use: Utilisation

    private val NomKey: String = "NOM"
    private val PrenomKey: String = "PRENOM"
    private val AgeKey: String = "AGE"
    private val NumKey: String = "NUMTEL"
    private val IDKey: String = "ID"
    private val NbUseKey: String = "NBUSE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inscription)
        use = Utilisation(0)
        EditNom = findViewById<EditText>(R.id.EditTextNom)
        EditPrenom = findViewById<EditText>(R.id.EditTextPrenom)
        EditAge = findViewById<EditText>(R.id.EditTextAge)
        EditNumTel = findViewById<EditText>(R.id.EditTextNumTel)
        EditPassword = findViewById<EditText>(R.id.EditTextPassword)
        inscriptionButton = findViewById<Button>(R.id.InscriptionButton)
        SubmitButton = findViewById<Button>(R.id.SubmitButton)
        PlanningButton = findViewById<Button>(R.id.PlanningButton)
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(NomKey)) {
                EditNom.setText(savedInstanceState.get(NomKey).toString())
            }
            if (savedInstanceState.containsKey(PrenomKey)) {
                EditPrenom.setText(savedInstanceState.get(PrenomKey).toString())
            }
            if (savedInstanceState.containsKey(AgeKey)) {
                EditAge.setText(savedInstanceState.get(AgeKey).toString())
            }
            if (savedInstanceState.containsKey(NumKey)) {
                EditNumTel.setText(savedInstanceState.get(NumKey).toString())
            }
            if (savedInstanceState.containsKey(IDKey)) {
                id = savedInstanceState.get(IDKey).toString().toInt()
            }
            if (savedInstanceState.containsKey(NbUseKey)) {
                println("------------${savedInstanceState.get(NbUseKey).toString()}")
                use.setNbUse(savedInstanceState.get(NbUseKey).toString().toInt())
            }
        }
        inscriptionButton.setOnClickListener {
            if ((EditNom.text.isBlank()) || (EditPrenom.text.isBlank()) || (EditAge.text.isBlank()) || (EditNumTel.text.isBlank()) || (EditPassword.text.isBlank())) {
                Toast.makeText(this, R.string.notInscrit, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT).show()
                id = GenerateId()
            }
        }

        SubmitButton.setOnClickListener {
            if ((EditNom.text.isBlank()) || (EditPrenom.text.isBlank()) || (EditAge.text.isBlank()) || (EditNumTel.text.isBlank()) || (EditPassword.text.isBlank())) {
                Toast.makeText(this, R.string.notInscrit, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT).show()
                id = GenerateId()
                var file: FileOutputStream = openFileOutput(
                    EditNom.text.toString().plus(id.toString()),
                    Context.MODE_PRIVATE
                )
                file.write(EditNom.text.toString().plus(",").toByteArray())
                file.write(EditPrenom.text.toString().plus(",").toByteArray())
                file.write(EditAge.text.toString().plus(",").toByteArray())
                file.write(EditNumTel.text.toString().plus(",").toByteArray())
                file.write(EditPassword.text.toString().plus(",").toByteArray())
                file.close()
                val intent: Intent = Intent(this, MainActivity2::class.java)
                intent.putExtra(NomKey, EditNom.text.toString())
                intent.putExtra(IDKey, id.toString())
                intent.putExtra(NbUseKey, use.getNbUse().toString())
                startActivity(intent)
            }
        }

        PlanningButton.setOnClickListener {
            val intent: Intent = Intent(this, Planning::class.java)
            startActivity(intent)
        }

        lifecycle.addObserver(use)

    }

    fun GenerateId(): Int {
        return (99999..1000000).random()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, R.string.onResume, Toast.LENGTH_SHORT).show()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NomKey, EditNom.text.toString())
        outState.putString(PrenomKey, EditPrenom.text.toString())
        outState.putString(AgeKey, EditAge.text.toString())
        outState.putString(NumKey, EditNumTel.text.toString())
        outState.putString(NbUseKey, use.getNbUse().toString())
        if (!id.equals(0)) outState.putString(IDKey, id.toString())
        Toast.makeText(this, R.string.savedInstance, Toast.LENGTH_SHORT).show()
    }

}