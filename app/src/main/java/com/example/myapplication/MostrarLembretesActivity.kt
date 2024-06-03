package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMostrarLembretesBinding

class MostrarLembretesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMostrarLembretesBinding
    private lateinit var db: DBHelper
    private lateinit var medicamentosAdapter: MedicamentosAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMostrarLembretesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DBHelper(this)
        medicamentosAdapter = MedicamentosAdapter(db.getAllMedicamentos(), this)

        binding.medicamentosRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.medicamentosRecyclerView.adapter = medicamentosAdapter


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        medicamentosAdapter.refreshData(db.getAllMedicamentos())
    }
}