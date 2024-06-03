package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityEditarConsultaBinding

class EditarConsultaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarConsultaBinding
    private lateinit var db: DBHelper
    private var consultaID: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarConsultaBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        db = DBHelper(this)

        consultaID = intent.getIntExtra("consulta_id", -1)
        if (consultaID == -1) {
            finish()
            return
        }

        val consulta = db.getConsultaByID(consultaID)
        binding.updateIdConsulta.setText(consulta.editidconsulta.toString())
        binding.updateNomePaciente.setText(consulta.editNomePaciente)
        binding.updatetDataConsulta.setText(consulta.editdataconsulta)
        binding.updateMotivoConsulta.setText(consulta.editmotivoconsulta)
        binding.updateLocalConsulta.setText(consulta.editlocalconsulta)

        binding.bottomSalvar.setOnClickListener {
            val newIdConsulta = binding.updateIdConsulta.text.toString().toInt()
            val neweditNomePaciente = binding.updateNomePaciente.text.toString()
            val neweditDataConsulta = binding.updatetDataConsulta.text.toString()
            val neweditMotivoConsulta = binding.updateMotivoConsulta.text.toString()
            val neweditLocalConsulta = binding.updateLocalConsulta.text.toString()

            val updateConsulta = Consulta(consultaID, neweditNomePaciente, newIdConsulta, neweditDataConsulta, neweditMotivoConsulta, neweditLocalConsulta)
            db.editarConsulta(updateConsulta)
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