package br.com.delecias.vida.deliciasapi.domain.repository

import br.com.delecias.vida.deliciasapi.domain.model.Estado

interface EstadoRepository {
    fun listar(): List<Estado>
    fun buscar(id: Long): Estado?
    fun salvar(estado: Estado): Estado
    fun remover(estado: Estado)
}