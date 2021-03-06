package br.com.delecias.vida.deliciasapi.domain.service

import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeEmUsoException
import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeNaoEncontradaException
import br.com.delecias.vida.deliciasapi.domain.model.Cidade
import br.com.delecias.vida.deliciasapi.domain.repository.CidadeRepository
import br.com.delecias.vida.deliciasapi.domain.repository.EstadoRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class CadastroCidadeService(
    val repoCidade: CidadeRepository,
    val repoEstado: EstadoRepository
) {

    fun salvar(cidade: Cidade): Cidade {

        val estadoId = cidade.estado.id

        val estado =
            repoEstado.findById(estadoId).orElseThrow{
                throw EntidadeNaoEncontradaException("Nao existe estado para id: $estadoId")
            }

        cidade.estado = estado

        return repoCidade.save(cidade)
    }

    fun excluir(cidadeId: Long) {
        try {
            repoCidade.deleteById(cidadeId)
        } catch (e: EmptyResultDataAccessException) {
            throw EntidadeNaoEncontradaException("Nao existe um cadastro de cidade com codigo: $cidadeId")
        } catch (e: DataIntegrityViolationException) {
            throw EntidadeEmUsoException("Cidade de id[$cidadeId], nao pode ser removida pois esta em uso.")
        }
    }

}
