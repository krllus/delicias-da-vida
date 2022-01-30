package br.com.delecias.vida.deliciasapi.domain.repository

import br.com.delecias.vida.deliciasapi.domain.model.Restaurante

interface RestauranteRepository {
    fun listar(): List<Restaurante>
    fun buscar(id: Long): Restaurante?
    fun salvar(cozinha: Restaurante): Restaurante
    fun remover(cozinha: Restaurante)
}