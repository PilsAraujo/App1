package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding . inflate (layoutInflater)
        setContentView(/* view = */ binding.root)
        
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets


            }
        binding.bottomAdicionarConsulta.setOnClickListener(this)
        binding.bottomEditarConsutla.setOnClickListener(this)
        binding.bottomMostrarConsulta.setOnClickListener(this)
        binding.bottomExcluirConsulta.setOnClickListener(this)
        binding.bottomAdicionarLembrete.setOnClickListener(this)
        binding.bottomEditarLembrete.setOnClickListener(this)
        binding.bottomMostarLembretes.setOnClickListener(this)
        binding.bottomExcluirLembrete.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.bottom_Adicionar_Consulta){
            startActivity(Intent(this, AdicionaConsultaActivity::class.java))
        }
        else if (view.id == R.id.bottom_Editar_Consutla){
            startActivity(Intent(this, EditarConsultaActivity::class.java))
        }
        else if (view.id == R.id.bottom_Mostrar_Consulta){
            startActivity(Intent(this, MostrarConsultasActivity::class.java))
        }
        else if (view.id == R.id.bottom_Excluir_Consulta){
            startActivity(Intent(this, ExcluirConsultaActivity::class.java))
        }
        else if (view.id == R.id.bottom_Adicionar_Lembrete){
            startActivity(Intent(this, AdicionaLembreteActivity::class.java))
        }
        else if (view.id == R.id.bottom_Editar_Lembrete){
            startActivity(Intent(this, EditarLembreteActivity::class.java))
        }
        else if (view.id == R.id.bottom_Mostar_Lembretes){
            startActivity(Intent(this, MostrarLembretesActivity::class.java))
        }
        else if (view.id == R.id.bottom_Excluir_Lembrete){
            startActivity(Intent(this, ExcluirLembreteActivity::class.java))
        }

    }




}






