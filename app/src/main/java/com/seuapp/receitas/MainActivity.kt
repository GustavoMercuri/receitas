package com.seuapp.receitas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ReceitasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Dao.inicializar()

        val recycler = findViewById<RecyclerView>(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(this)

        adapter = ReceitasAdapter(
            Dao.listar(),
            onItemLongClick = { receita ->
                AlertDialog.Builder(this)
                    .setTitle("Remover receita")
                    .setMessage("Deseja remover \"${receita.nome}\"?")
                    .setPositiveButton("Remover") { _, _ ->
                        Dao.remover(receita.id)
                        adapter.notifyDataSetChanged()
                        Toast.makeText(this, "Receita removida!", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Cancelar", null)
                    .show()
            },
            onNameClick = { receita ->
                startActivity(
                    Intent(this, ReceitaDetalheActivity::class.java)
                        .putExtra("id", receita.id)
                )
            }
        )

        recycler.adapter = adapter

        val btnAdd: Button = findViewById(R.id.btnAdd)
        val btnLimpar: Button = findViewById(R.id.btnLimpar)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddReceitaActivity::class.java))
        }

        btnLimpar.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Limpar tudo")
                .setMessage("Tem certeza que deseja apagar TODAS as receitas?")
                .setPositiveButton("Limpar") { _, _ ->
                    Dao.limpar()
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Lista apagada!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}

