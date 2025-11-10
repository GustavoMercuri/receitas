package com.seuapp.receitas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditReceitaActivity : AppCompatActivity() {
    private var id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_receita)

        id = intent.getIntExtra("id", -1)
        val receita = Dao.buscarPorId(id)
        if (id == -1 || receita == null) {
            Toast.makeText(this, "Receita não encontrada", Toast.LENGTH_SHORT).show()
            finish(); return
        }

        val etNome = findViewById<EditText>(R.id.etNome)
        val etIng  = findViewById<EditText>(R.id.etIngredientes)
        val etModo = findViewById<EditText>(R.id.etModo)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        etNome.setText(receita.nome)
        etIng.setText(receita.ingredientes)
        etModo.setText(receita.modo)

        btnSalvar.setOnClickListener {
            val nome = etNome.text.toString().trim()
            val ing  = etIng.text.toString().trim()
            val modo = etModo.text.toString().trim()
            if (nome.isEmpty()) { etNome.error = "Informe o nome"; return@setOnClickListener }
            if (ing.isEmpty())  { etIng.error  = "Informe os ingredientes"; return@setOnClickListener }
            if (modo.isEmpty()) { etModo.error = "Informe o modo"; return@setOnClickListener }

            Dao.editar(id, nome, ing, modo)
            Toast.makeText(this, "Receita atualizada!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
