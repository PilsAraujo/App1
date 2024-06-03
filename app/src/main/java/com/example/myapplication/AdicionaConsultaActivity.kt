package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityAdicionaConsultaBinding

class AdicionaConsultaActivity : AppCompatActivity() {


    private lateinit var binding: ActivityAdicionaConsultaBinding
    private lateinit var db: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdicionaConsultaBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        db = DBHelper(this)

        binding.bottomAddConsulta.setOnClickListener {
            val editNomePaciente = binding.editNomePaciente.text.toString()
            val editIdconsulta = binding.editIdConsulta.text.toString().toInt()
            val editDataConsulta = binding.editDataConsulta.text.toString()
            val editMotivoConsulta = binding.editMotivoConsulta.text.toString()
            val editLocalConsulta = binding.editLocalConsulta.text.toString()

            val consulta = Consulta(0,editNomePaciente, editIdconsulta, editDataConsulta, editMotivoConsulta, editLocalConsulta)
            db.addConsulta(consulta)
            finish()
            Toast.makeText(this, "Consulta Adicionada", Toast.LENGTH_SHORT).show()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}