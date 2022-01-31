package br.com.delecias.vida.deliciasapi.infrastructure.repository

import br.com.delecias.vida.deliciasapi.domain.model.Cozinha
import br.com.delecias.vida.deliciasapi.domain.model.Estado
import br.com.delecias.vida.deliciasapi.domain.repository.EstadoRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class EstadoRepositoryImpl(
    @PersistenceContext val manager: EntityManager
) : EstadoRepository {
    override fun listar(): List<Estado> {
        val query = manager.createQuery("from Estado", Estado::class.java)

        return query.resultList
    }

    override fun buscar(id: Long): Estado? {
        return manager.find(Estado::class.java, id)
    }

    @Transactional
    override fun salvar(estado: Estado): Estado {
        return manager.merge(estado)
    }

    @Transactional
    override fun remover(estado: Estado) {
        return manager.remove(estado)
    }
}