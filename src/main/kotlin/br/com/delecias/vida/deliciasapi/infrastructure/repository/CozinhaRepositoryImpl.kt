package br.com.delecias.vida.deliciasapi.infrastructure.repository

import br.com.delecias.vida.deliciasapi.domain.model.Cozinha
import br.com.delecias.vida.deliciasapi.domain.repository.CozinhaRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class CozinhaRepositoryImpl(
    @PersistenceContext val manager: EntityManager
) : CozinhaRepository {

    override fun listar(): List<Cozinha> {
        val query = manager.createQuery("from Cozinha", Cozinha::class.java)

        return query.resultList
    }

    override fun listarCozinhasPorNome(nome: String): List<Cozinha> {
        return manager.createQuery("from Cozinha where nome like :nome", Cozinha::class.java)
            .setParameter("nome", "%$nome%")
            .resultList
    }

    override fun buscar(id: Long): Cozinha? {
        return manager.find(Cozinha::class.java, id)
    }

    @Transactional
    override fun salvar(cozinha: Cozinha): Cozinha {
        return manager.merge(cozinha)
    }

    @Transactional
    override fun remover(cozinhaId: Long) {
        val cozinha = buscar(cozinhaId) ?: throw EmptyResultDataAccessException(1)
        return manager.remove(cozinha)
    }


}