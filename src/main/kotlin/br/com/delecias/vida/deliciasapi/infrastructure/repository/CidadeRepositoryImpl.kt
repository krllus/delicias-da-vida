package br.com.delecias.vida.deliciasapi.infrastructure.repository

import br.com.delecias.vida.deliciasapi.domain.model.Cidade
import br.com.delecias.vida.deliciasapi.domain.model.Cozinha
import br.com.delecias.vida.deliciasapi.domain.repository.CidadeRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class CidadeRepositoryImpl(
    @PersistenceContext val manager: EntityManager
) : CidadeRepository{
    override fun listar(): List<Cidade> {
        val query = manager.createQuery("from Cidade", Cidade::class.java)

        return query.resultList
    }

    override fun buscar(id: Long): Cidade? {
        return manager.find(Cidade::class.java, id)
    }

    @Transactional
    override fun salvar(cidade: Cidade): Cidade {
        return manager.merge(cidade)
    }

    @Transactional
    override fun remover(cidadeId: Long) {
        val cidade = buscar(cidadeId) ?: throw EmptyResultDataAccessException(1)
        return manager.remove(cidade)
    }
}