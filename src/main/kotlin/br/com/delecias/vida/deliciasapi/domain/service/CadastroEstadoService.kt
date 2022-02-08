package br.com.delecias.vida.deliciasapi.domain.service

import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeEmUsoException
import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeNaoEncontradaException
import br.com.delecias.vida.deliciasapi.domain.model.Cozinha
import br.com.delecias.vida.deliciasapi.domain.model.Estado
import br.com.delecias.vida.deliciasapi.domain.repository.CozinhaRepository
import br.com.delecias.vida.deliciasapi.domain.repository.EstadoRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class CadastroEstadoService(
    val repoEstado: EstadoRepository
) {

    fun salvar(estado: Estado): Estado {
        return repoEstado.salvar(estado)
    }

    fun excluir(estadoId: Long) {
        try {
            repoEstado.remover(estadoId)
        } catch (e: EmptyResultDataAccessException) {
            throw EntidadeNaoEncontradaException("Nao existe um cadastro de estado com codigo: $estadoId")
        } catch (e: DataIntegrityViolationException) {
            throw EntidadeEmUsoException("Estado de codig: $estadoId, nao pode ser removido pois esta em uso.")        }
    }

}
