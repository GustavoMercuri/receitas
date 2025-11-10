package com.seuapp.receitas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class ReceitaDetalheActivity : AppCompatActivity() {
    private var id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receita_detalhe)

        id = intent.getIntExtra("id", -1)
        val receita = Dao.buscarPorId(id)
        if (id == -1 || receita == null) {
            Toast.makeText(this, "Receita não encontrada", Toast.LENGTH_SHORT).show()
            finish(); return
        }

        val tvNome = findViewById<TextView>(R.id.tvNome)
        val tvIng  = findViewById<TextView>(R.id.tvIngredientes)
        val tvModo = findViewById<TextView>(R.id.tvModo)
        val btnEditar = findViewById<Button>(R.id.btnEditar)

        tvNome.text = receita.nome
        tvIng.text  = receita.ingredientes
        tvModo.text = receita.modo

        btnEditar.setOnClickListener {
            startActivity(Intent(this, EditReceitaActivity::class.java).putExtra("id", id))
        }
    }

    override fun onResume() {
        super.onResume()
        val r = Dao.buscarPorId(id) ?: return
        findViewById<TextView>(R.id.tvNome).text = r.nome
        findViewById<TextView>(R.id.tvIngredientes).text = r.ingredientes
        findViewById<TextView>(R.id.tvModo).text = r.modo
    }
}
