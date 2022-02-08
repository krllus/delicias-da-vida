package br.com.delecias.vida.deliciasapi.domain.service

import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeEmUsoException
import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeNaoEncontradaException
import br.com.delecias.vida.deliciasapi.domain.model.Cozinha
import br.com.delecias.vida.deliciasapi.domain.repository.CozinhaRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class CadastroCozinhaService(
    val repoCozinha: CozinhaRepository
) {

    fun salvar(cozinha: Cozinha): Cozinha {
        return repoCozinha.salvar(cozinha)
    }

    fun excluir(cozinhaId: Long) {
        try {
            repoCozinha.remover(cozinhaId)
        } catch (e: EmptyResultDataAccessException) {
            throw EntidadeNaoEncontradaException("Nao existe um cadastro de cozinnha com codigo: $cozinhaId")
        } catch (e: DataIntegrityViolationException) {
            throw EntidadeEmUsoException("Cozinha de codigo: $cozinhaId, nao pode ser removida pois esta em uso.")
        }
    }

}
