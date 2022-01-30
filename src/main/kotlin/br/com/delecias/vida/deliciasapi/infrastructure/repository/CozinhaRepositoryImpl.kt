package br.com.delecias.vida.deliciasapi.infrastructure.repository

import br.com.delecias.vida.deliciasapi.domain.model.Cozinha
import br.com.delecias.vida.deliciasapi.domain.repository.CozinhaRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class CozinhaRepositoryImpl(
    @PersistenceContext val manager: EntityManager
) : CozinhaRepository {

    override fun listar(): List<Cozinha> {
        val query = manager.createQuery("from Cozinha", Cozinha::class.java)

        return query.resultList
    }

    override fun buscar(id: Long): Cozinha? {
        return manager.find(Cozinha::class.java, id)
    }

    @Transactional
    override fun salvar(cozinha: Cozinha): Cozinha {
        return manager.merge(cozinha)
    }

    @Transactional
    override fun remover(cozinha: Cozinha) {
        return manager.remove(cozinha)
    }
}