package br.com.delecias.vida.deliciasapi.domain.repository

import br.com.delecias.vida.deliciasapi.domain.model.Cozinha

interface CozinhaRepository {
    fun listar(): List<Cozinha>
    fun buscar(id: Long): Cozinha?
    fun salvar(cozinha: Cozinha): Cozinha
    fun remover(cozinha: Long)
}