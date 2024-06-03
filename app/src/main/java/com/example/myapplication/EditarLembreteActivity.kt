package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityEditarLembreteBinding

class EditarLembreteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarLembreteBinding
    private lateinit var db: DBHelper
    private var medicamentoID: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarLembreteBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        db = DBHelper(this)

        medicamentoID = intent.getIntExtra("medicamento_id", -1)
        if (medicamentoID == -1) {
            finish()
            return
        }

        val medicamento = db.getMedicamentoByID(medicamentoID)
        binding.updateIdAgendamento.setText(medicamento.editidagendamento.toString())
        binding.updateNomePaciente.setText(medicamento.editnomedopaciente)
        binding.updateNomeMedicamento.setText(medicamento.editnomemedicamento)
        binding.updateLocalConsulta.setText(medicamento.editlocalconsulta)
        binding.updateData.setText(medicamento.editdata)

        binding.bottomSalvar.setOnClickListener {
            val newIdAgendamento = binding.updateIdAgendamento.text.toString().toInt()
            val neweditnomedopaciente = binding.updateNomePaciente.text.toString()
            val neweditnomemedicamento = binding.updateNomeMedicamento.text.toString()
            val neweditlocalconsulta = binding.updateLocalConsulta.text.toString()
            val neweditdata = binding.updateData.text.toString()

            val updateMedicamento = Medicamento(medicamentoID, newIdAgendamento, neweditnomedopaciente, neweditnomemedicamento, neweditlocalconsulta, neweditdata)
            db.editarMedicamento(updateMedicamento)
            finish()
            Toast.makeText(this, "Mudança feita", Toast.LENGTH_SHORT).show()
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}