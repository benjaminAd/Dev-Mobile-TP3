package com.example.myapplication

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.io.FileInputStream
import java.io.FileOutputStream

class Planning : AppCompatActivity() {

    private lateinit var planning1: TextView
    private lateinit var planning2: TextView
    private lateinit var planning3: TextView
    private lateinit var planning4: TextView

    private lateinit var context: Context

    private lateinit var buttonFillData: Button

    private lateinit var file: FileOutputStream

    private lateinit var mPlanningAndroidViewModel: PlanningAndroidViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planning)
        planning1 = findViewById<TextView>(R.id.Planning1)
        planning2 = findViewById<TextView>(R.id.Planning2)
        planning3 = findViewById<TextView>(R.id.Planning3)
        planning4 = findViewById<TextView>(R.id.Planning4)
        context = this

        mPlanningAndroidViewModel =
            ViewModelProvider(this).get(PlanningAndroidViewModel(application)::class.java)

        // mPlanningAndroidViewModel.addBasicsData()

        mPlanningAndroidViewModel.readAllData.observe(this, { plannings ->
            planning1.text = "${plannings[0].horaire} : ${plannings[0].activity}"
            planning2.text = "${plannings[1].horaire} : ${plannings[1].activity}"
            planning3.text = "${plannings[2].horaire} : ${plannings[2].activity}"
            planning4.text = "${plannings[3].horaire} : ${plannings[3].activity}"

        })

        /* val planningViewModel: PlanningViewModel =
             ViewModelProvider(this)[PlanningViewModel::class.java]
         planningViewModel.getPlanning().observe(this, { slots ->
             planning1.text = slots[0].toString()
             planning2.text = slots[1].toString()
             planning3.text = slots[2].toString()
             planning4.text = slots[3].toString()

         })*/

        Handler().postDelayed({
            file = openFileOutput("planning", Context.MODE_PRIVATE)
            file.write("8h-10h|Developpement Mobile,".toByteArray())
            file.write("10h-12h|Représentation des connaissances,".toByteArray())
            file.write("14h-16h|Web Sémantique,".toByteArray())
            file.write("16h-18h|TER,".toByteArray())
            file.close()
            Toast.makeText(context, R.string.saveMessage, Toast.LENGTH_SHORT).show()
        }, 20000)


        buttonFillData = findViewById<Button>(R.id.buttonFillData)
        /*buttonFillData.setOnClickListener {
            val fileInput: FileInputStream = openFileInput("planning")
            val byteArray: ByteArray = ByteArray(1024)
            fileInput.read(byteArray)
            val SplitData = String(byteArray).split(",")
            val creneauList: ArrayList<Creneau> = ArrayList()
            for (i in 0..3) {
                val SplitCreneau = SplitData[i].split("|")
                creneauList.add(Creneau(SplitCreneau[0], SplitCreneau[1]))
            }
            planningViewModel.getPlanning().value = creneauList
        }*/
    }
}