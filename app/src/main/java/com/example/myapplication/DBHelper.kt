package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "app.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_CON = "Consultas"
        private const val COLUMN_ID_CON = "idconsulta"
        private const val COLUMN_NOME = "editNomePaciente"
        private const val COLUMN_NUM_CON = "editidconsulta"
        private const val COLUMN_DATA_CON = "editdataconsulta"
        private const val COLUMN_MOTIVO = "editmotivoconsulta"
        private const val COLUMN_LOCAL = "editlocalconsulta"

        private const val TABLE_MED = "Lembretes"
        private const val COLUMN_ID_MED = "idlembrete"
        private const val COLUMN_NUM_MED = "editIdAgendamento"
        private const val COLUMN_NAME = "editNomePaciente"
        private const val COLUMN_NOME_MED = "editNomeMedicamento"
        private const val COLUMN_DOSAGEM = "editLocalConsulta"
        private const val COLUMN_DATA = "editData"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_CON($COLUMN_ID_CON INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NOME TEXT NOT NULL, $COLUMN_NUM_CON INTEGER NOT NULL, $COLUMN_DATA_CON TEXT NOT NULL, $COLUMN_MOTIVO TEXT NOT NULL, $COLUMN_LOCAL TEXT NOT NULL)"
        db?.execSQL(createTableQuery)

        val createTableMedQuery = "CREATE TABLE $TABLE_MED($COLUMN_ID_MED INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT NOT NULL, $COLUMN_NUM_MED INTEGER NOT NULL, $COLUMN_NOME_MED TEXT NOT NULL, $COLUMN_DOSAGEM TEXT NOT NULL, $COLUMN_DATA TEXT NOT NULL)"
        db?.execSQL(createTableMedQuery)
    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addConsulta(consulta: Consulta) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME, consulta.editNomePaciente)
            put(COLUMN_NUM_CON, consulta.editidconsulta)
            put(COLUMN_DATA_CON, consulta.editdataconsulta)
            put(COLUMN_MOTIVO, consulta.editmotivoconsulta)
            put(COLUMN_LOCAL, consulta.editlocalconsulta)
        }
        db.insert(TABLE_CON, null, values)
        db.close()
    }

    fun addMedicamento(medicamento: Medicamento) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NUM_MED, medicamento.editidagendamento)
            put(COLUMN_NAME, medicamento.editnomedopaciente)
            put(COLUMN_NOME_MED, medicamento.editnomemedicamento)
            put(COLUMN_DOSAGEM, medicamento.editlocalconsulta)
            put(COLUMN_DATA, medicamento.editdata)
        }
        db.insert(TABLE_MED, null, values)
        db.close()
    }

    fun getAllConsultas() : List<Consulta> {
        val consultasList = mutableListOf<Consulta>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_CON"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val idconsulta = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_CON))
            val editNomePaciente = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME))
            val editidconsulta = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUM_CON))
            val editdataconsulta = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA_CON))
            val editmotivoconsulta = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOTIVO))
            val editlocalconsulta = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCAL))

            val consulta = Consulta(idconsulta, editNomePaciente, editidconsulta, editdataconsulta, editmotivoconsulta, editlocalconsulta)
            consultasList.add(consulta)
        }

        cursor.close()
        db.close()
        return consultasList
    }

    fun getAllMedicamentos() : List<Medicamento> {
        val medicamentosList = mutableListOf<Medicamento>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_MED"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val idlembrete = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_MED))
            val editagendamento = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUM_MED))
            val editNomeDoPaciente = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val editnomemedicamento = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME_MED))
            val editlocalconsulta = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOSAGEM))
            val editdata = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA))

            val medicamento = Medicamento(idlembrete, editagendamento, editNomeDoPaciente, editnomemedicamento, editlocalconsulta, editdata)
            medicamentosList.add(medicamento)
        }

        cursor.close()
        db.close()
        return medicamentosList
    }

    fun editarConsulta(consulta: Consulta) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME, consulta.editNomePaciente)
            put(COLUMN_NUM_CON, consulta.editidconsulta)
            put(COLUMN_DATA_CON, consulta.editdataconsulta)
            put(COLUMN_MOTIVO, consulta.editmotivoconsulta)
            put(COLUMN_LOCAL, consulta.editlocalconsulta)
        }

        val whereClause = "$COLUMN_ID_CON = ?"
        val whereArgs = arrayOf(consulta.idconsulta.toString())
        db.update(TABLE_CON, values, whereClause, whereArgs)
        db.close()
    }

    fun getConsultaByID(consultaID: Int): Consulta {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_CON WHERE $COLUMN_ID_CON = $consultaID"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val idconsulta = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_CON))
        val editNomePaciente = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME))
        val editidconsulta = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUM_CON))
        val editdataconsulta = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA_CON))
        val editmotivoconsulta = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOTIVO))
        val editlocalconsulta = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCAL))

        cursor.close()
        db.close()
        return Consulta(idconsulta, editNomePaciente, editidconsulta, editdataconsulta, editmotivoconsulta, editlocalconsulta)
    }

    fun editarMedicamento(medicamento: Medicamento) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NUM_MED, medicamento.editidagendamento)
            put(COLUMN_NAME, medicamento.editnomedopaciente)
            put(COLUMN_NOME_MED, medicamento.editnomemedicamento)
            put(COLUMN_DOSAGEM, medicamento.editlocalconsulta)
            put(COLUMN_DATA, medicamento.editdata)
        }

        val whereClause = "$COLUMN_ID_MED = ?"
        val whereArgs = arrayOf(medicamento.idlembrete.toString())
        db.update(TABLE_MED, values, whereClause, whereArgs)
        db.close()
    }

    fun getMedicamentoByID(medicamentoID: Int): Medicamento {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_MED WHERE $COLUMN_ID_MED = $medicamentoID"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val idlembrete = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_MED))
        val editNomedoPaciente = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
        val editidagendamento = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUM_MED))
        val editnomemedicamento = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME_MED))
        val editlocalconsulta = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOSAGEM))
        val editdata = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA))

        cursor.close()
        db.close()
        return Medicamento(idlembrete, editidagendamento, editNomedoPaciente, editnomemedicamento, editlocalconsulta, editdata)
    }

    fun deleteConsulta(consultaID: Int) {
        val db = writableDatabase
        val whereClause = "$COLUMN_ID_CON = ?"
        val whereArgs = arrayOf(consultaID.toString())

        db.delete(TABLE_CON, whereClause, whereArgs)
        db.close()
    }

    fun deleteMedicamento(medicamentoID: Int) {
        val db = writableDatabase
        val whereClause = "$COLUMN_ID_MED = ?"
        val whereArgs = arrayOf(medicamentoID.toString())

        db.delete(TABLE_MED, whereClause, whereArgs)
        db.close()
    }
}