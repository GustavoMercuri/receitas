package com.seuapp.receitas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddReceitaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_receita)

        val etNome = findViewById<EditText>(R.id.etNome)
        val etIng  = findViewById<EditText>(R.id.etIngredientes)
        val etModo = findViewById<EditText>(R.id.etModo)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        btnSalvar.setOnClickListener {
            val nome = etNome.text.toString().trim()
            val ing  = etIng.text.toString().trim()
            val modo = etModo.text.toString().trim()

            if (nome.isEmpty()) { etNome.error = "Informe o nome"; return@setOnClickListener }
            if (ing.isEmpty())  { etIng.error  = "Informe os ingredientes"; return@setOnClickListener }
            if (modo.isEmpty()) { etModo.error = "Informe o modo"; return@setOnClickListener }

            Dao.adicionar(nome, ing, modo)
            Toast.makeText(this, "Receita adicionada!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
