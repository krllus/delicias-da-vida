package br.com.delecias.vida.deliciasapi.domain.repository

import br.com.delecias.vida.deliciasapi.domain.model.Cidade

interface CidadeRepository {
    fun listar(): List<Cidade>
    fun buscar(id: Long): Cidade?
    fun salvar(cidade: Cidade): Cidade
    fun remover(cidadeId: Long)
}