package br.com.delecias.vida.deliciasapi.infrastructure.repository

import br.com.delecias.vida.deliciasapi.domain.model.Cozinha
import br.com.delecias.vida.deliciasapi.domain.model.Permissao
import br.com.delecias.vida.deliciasapi.domain.repository.PermissaoRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class PermissaoRepositoryImpl(
    @PersistenceContext val manager: EntityManager
) : PermissaoRepository {
    override fun listar(): List<Permissao> {
        val query = manager.createQuery("from Permissao", Permissao::class.java)

        return query.resultList
    }

    override fun buscar(id: Long): Permissao? {
        return manager.find(Permissao::class.java, id)
    }

    @Transactional
    override fun salvar(permissao: Permissao): Permissao {
        return manager.merge(permissao)
    }

    @Transactional
    override fun remover(permissaoId: Long) {
        val permissao = buscar(permissaoId) ?: throw EmptyResultDataAccessException(1)
        return manager.remove(permissao)
    }
}