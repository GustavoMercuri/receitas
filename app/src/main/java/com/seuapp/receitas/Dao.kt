package com.seuapp.receitas

object Dao {

    fun inicializar() {
        if (receitas.isEmpty()) gerarExemplos()
    }

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
    fun limpar() {
        receitas.clear()
        seqId = 1
    }

    private fun gerarExemplos() {
        if (receitas.isNotEmpty()) return
        adicionar(
            "Bolo de Chocolate",
            "2 xic farinha; 3 ovos; 1 xic açúcar; 1/2 xic cacau; 1 xic leite; 2 col sopa óleo; 1 col sopa fermento",
            "Misture secos; some líquidos; asse 180 °C ~40min."
        )
        adicionar(
            "Panqueca Simples",
            "1 xic leite; 1 xic farinha; 1 ovo; pitada de sal",
            "Bata tudo; unte frigideira; doure dos dois lados."
        )
        adicionar(
            "Suco Verde",
            "1 folha couve; 1 maçã; suco de 1/2 limão; 200 ml água",
            "Bata no liquidificador e sirva gelado."
        )
    }
}
