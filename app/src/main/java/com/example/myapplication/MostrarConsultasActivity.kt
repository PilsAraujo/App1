package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMostrarConsultasBinding

class MostrarConsultasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMostrarConsultasBinding
    private lateinit var db: DBHelper
    private lateinit var consultasAdapter: ConsultasAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMostrarConsultasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DBHelper(this)
        consultasAdapter = ConsultasAdapter(db.getAllConsultas(), this)

        binding.consultasRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.consultasRecyclerView.adapter = consultasAdapter




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    override fun onResume() {
        super.onResume()
        consultasAdapter.refreshData(db.getAllConsultas())
    }
}
