package br.com.delecias.vida.deliciasapi.domain.repository

import br.com.delecias.vida.deliciasapi.domain.model.FormaPagamento

interface FormaPagamentoRepository {
    fun listar(): List<FormaPagamento>
    fun buscar(id: Long): FormaPagamento?
    fun salvar(formaPagamento: FormaPagamento): FormaPagamento
    fun remover(formaPagamento: FormaPagamento)
}