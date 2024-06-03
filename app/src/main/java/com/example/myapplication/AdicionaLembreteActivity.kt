package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityAdicionaLembreteBinding

class AdicionaLembreteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdicionaLembreteBinding
    private lateinit var db: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdicionaLembreteBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        db = DBHelper(this)

        binding.bottomSalvar.setOnClickListener {
            val editidagendamento = binding.editIdAgendamento.text.toString().toInt()
            val editnomedopaciente = binding.editNomePaciente.text.toString()
            val editnomemedicamento = binding.editNomeMedicamento.text.toString()
            val editlocalconsulta = binding.editLocalConsulta.text.toString()
            val editdata = binding.editData.text.toString()

            val medicamento = Medicamento(0, editidagendamento, editnomedopaciente, editnomemedicamento, editlocalconsulta, editdata)
            db.addMedicamento(medicamento)
            finish()

            Toast.makeText(this, "Medicamento Salvo", Toast.LENGTH_SHORT).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}