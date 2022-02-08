package br.com.delecias.vida.deliciasapi.infrastructure.repository

import br.com.delecias.vida.deliciasapi.domain.model.Restaurante
import br.com.delecias.vida.deliciasapi.domain.repository.RestauranteRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class RestauranteRepositoryImpl(
    @PersistenceContext val manager: EntityManager
) : RestauranteRepository {

    override fun listar(): List<Restaurante> {
        val query = manager.createQuery("from Restaurante", Restaurante::class.java)

        return query.resultList
    }

    override fun buscar(id: Long): Restaurante? {
        return manager.find(Restaurante::class.java, id)
    }

    @Transactional
    override fun salvar(restaurante: Restaurante): Restaurante {
        return manager.merge(restaurante)
    }

    @Transactional
    override fun remover(restauranteId: Long) {

        val restaurante = buscar(restauranteId) ?: throw EmptyResultDataAccessException(1)

        return manager.remove(restaurante)
    }
}