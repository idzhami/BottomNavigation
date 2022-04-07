package id.idzhami.tutorialrecycleviewkotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.idzhami.tutorialrecycleviewkotlin.adapter.RecycleViewAdapter
import id.idzhami.tutorialrecycleviewkotlin.model.dataModel
import java.lang.Exception
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private var adapterTransaction by Delegates.notNull<RecycleViewAdapter>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            val listData = listOf(
                dataModel("Test 0" ,250000,"23-Juli-2021"),
                dataModel("Test 1" ,4500000,"01-April-2022"),
                dataModel("Test 2" ,70000,"22-Mei-2022"),
                dataModel("Test 3" ,158000,"17-Maret-2022"),
            )
            adapterTransaction = RecycleViewAdapter(
                this,
                listData)
            val rvmain = findViewById<RecyclerView>(R.id.rv_main)

               rvmain.layoutManager = LinearLayoutManager(this@MainActivity)
                rvmain.adapter = adapterTransaction

        }catch (e : Exception){
            Log.e("Error : ",e.toString())
        }
    }
}