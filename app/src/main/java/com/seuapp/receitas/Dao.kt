package com.seuapp.receitas

object Dao {
    private val receitas = ArrayList<Receita>()
    private var seqId = 1
    fun listar(): ArrayList<Receita> = receitas
    fun adicionar(nome: String, ingredientes: String, modo: String) {
        receitas.add(Receita(seqId++, nome, ingredientes, modo))
    }
    fun remover(id: Int) {
        val i = receitas.indexOfFirst { it.id == id }
        if (i >= 0) receitas.removeAt(i)
    }
    fun buscarPorId(id: Int): Receita? = receitas.firstOrNull { it.id == id }
    fun editar(id: Int, nome: String, ingredientes: String, modo: String) {
        val r = buscarPorId(id) ?: return
        r.nome = nome
        r.ingredientes = ingredientes
        r.modo = modo
    }
    fun reset() {
        receitas.clear()
        seqId = 1
    }
}
