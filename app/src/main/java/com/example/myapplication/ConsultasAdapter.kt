package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class ConsultasAdapter(private var consultas: List<Consulta>, context: Context) :
    RecyclerView.Adapter<ConsultasAdapter.ConsultaViewHolder>() {

    private val db: DBHelper = DBHelper(context)
    class ConsultaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val editIdConsultaTextView: TextView = itemView.findViewById(R.id.editIdConsultaTextView)
        val editNomePacienteTextView: TextView = itemView.findViewById(R.id.editNomePacienteTextView)
        val editdataconsultaTextView: TextView = itemView.findViewById(R.id.editdataconsultaTextView)
        val editmotivoconsultaTextView: TextView = itemView.findViewById(R.id.editmotivoconsultaTextView)
        val editlocalconsultaTextView: TextView = itemView.findViewById(R.id.editlocalconsultaTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.consulta_item, parent, false)
        return ConsultaViewHolder(view)
    }

    override fun getItemCount(): Int = consultas.size


    override fun onBindViewHolder(holder: ConsultaViewHolder, position: Int) {
        val consulta = consultas[position]
        holder.editIdConsultaTextView.text= consulta.editidconsulta.toString()
        holder.editNomePacienteTextView.text = consulta.editNomePaciente
        holder.editdataconsultaTextView.text = consulta.editdataconsulta
        holder.editmotivoconsultaTextView.text = consulta.editmotivoconsulta
        holder.editlocalconsultaTextView.text = consulta.editlocalconsulta

        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context, EditarConsultaActivity::class.java).apply {
                putExtra("consulta_id", consulta.idconsulta)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            db.deleteConsulta(consulta.idconsulta)
            refreshData(db.getAllConsultas())
            Toast.makeText(holder.itemView.context, "Consulta Excluída", Toast.LENGTH_SHORT).show()
        }

    }

    fun refreshData(newConsultas: List<Consulta>) {
        consultas = newConsultas
        notifyDataSetChanged()
    }
}









//class MostrarConsultasActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_mostrar_consultas)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//    }
//}