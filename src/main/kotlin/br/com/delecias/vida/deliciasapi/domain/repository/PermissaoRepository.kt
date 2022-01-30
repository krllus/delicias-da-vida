package br.com.delecias.vida.deliciasapi.domain.repository

import br.com.delecias.vida.deliciasapi.domain.model.Permissao

interface PermissaoRepository {
    fun listar(): List<Permissao>
    fun buscar(id: Long): Permissao?
    fun salvar(permissao: Permissao): Permissao
    fun remover(permissao: Permissao)
}