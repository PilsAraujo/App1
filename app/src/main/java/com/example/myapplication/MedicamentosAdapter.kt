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

class MedicamentosAdapter(private var medicamentos: List<Medicamento>, context: Context) :
    RecyclerView.Adapter<MedicamentosAdapter.MedicamentoViewHolder>() {

    private val db: DBHelper = DBHelper(context)
    class MedicamentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val editIdAgendamentoTextView: TextView = itemView.findViewById(R.id.editIdAgendamentoTextView)
        val editNomePacienteTextView: TextView = itemView.findViewById(R.id.editNomePacienteTextView)
        val editNomeMedicamentoTextView: TextView = itemView.findViewById(R.id.editNomeMedicamentoTextView)
        val editLocalConsultaTextView: TextView = itemView.findViewById(R.id.editLocalConsultaTextView)
        val editDataTextView: TextView = itemView.findViewById(R.id.editDataTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.medicamento_item, parent, false)
        return MedicamentoViewHolder(view)
    }

    override fun getItemCount(): Int = medicamentos.size

    override fun onBindViewHolder(holder: MedicamentoViewHolder, position: Int) {
        val medicamento = medicamentos[position]
        holder.editIdAgendamentoTextView.text = medicamento.editidagendamento.toString()
        holder.editNomePacienteTextView.text = medicamento.editnomedopaciente
        holder.editNomeMedicamentoTextView.text = medicamento.editnomemedicamento
        holder.editLocalConsultaTextView.text = medicamento.editlocalconsulta
        holder.editDataTextView.text = medicamento.editdata

        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context, EditarLembreteActivity::class.java).apply {
                putExtra("medicamento_id", medicamento.idlembrete)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            db.deleteMedicamento(medicamento.idlembrete)
            refreshData(db.getAllMedicamentos())
            Toast.makeText(holder.itemView.context, "Medicamento Excluído", Toast.LENGTH_SHORT).show()
        }

    }

    fun refreshData(newMedicamentos: List<Medicamento>) {
        medicamentos = newMedicamentos
        notifyDataSetChanged()

    }

}