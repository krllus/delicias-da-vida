package br.com.delecias.vida.deliciasapi.infrastructure.repository

import br.com.delecias.vida.deliciasapi.domain.model.FormaPagamento
import br.com.delecias.vida.deliciasapi.domain.repository.FormaPagamentoRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class FormaPagamentoRepositoryImpl(
    @PersistenceContext val manager: EntityManager
) : FormaPagamentoRepository {
    override fun listar(): List<FormaPagamento> {
        val query = manager.createQuery("from FormaPagamento", FormaPagamento::class.java)

        return query.resultList
    }

    override fun buscar(id: Long): FormaPagamento? {
        return manager.find(FormaPagamento::class.java, id)
    }

    @Transactional
    override fun salvar(formaPagamento: FormaPagamento): FormaPagamento {
        return manager.merge(formaPagamento)
    }

    @Transactional
    override fun remover(formaPagamento: FormaPagamento) {
        return manager.remove(formaPagamento)
    }
}