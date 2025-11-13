package com.seuapp.receitas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class ReceitasAdapter(
    private val lista: ArrayList<Receita>,
    private val onItemLongClick: (Receita) -> Unit,
    private val onNameClick: (Receita) -> Unit
) : RecyclerView.Adapter<ReceitasAdapter.ViewHolder>() {

    init { setHasStableIds(true) }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome: TextView = itemView.findViewById(R.id.tvNome)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_receita, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val r = lista[position]
        holder.tvNome.text = r.nome

        holder.itemView.setOnClickListener { onNameClick(r) }
        holder.itemView.setOnLongClickListener {
            onItemLongClick(r)
            true
        }
    }

    override fun getItemCount(): Int = lista.size
    override fun getItemId(position: Int): Long = lista[position].id.toLong()
}
