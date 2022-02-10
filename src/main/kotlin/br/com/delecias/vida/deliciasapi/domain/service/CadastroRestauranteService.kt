package br.com.delecias.vida.deliciasapi.domain.service

import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeEmUsoException
import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeNaoEncontradaException
import br.com.delecias.vida.deliciasapi.domain.model.Restaurante
import br.com.delecias.vida.deliciasapi.domain.repository.CozinhaRepository
import br.com.delecias.vida.deliciasapi.domain.repository.RestauranteRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class CadastroRestauranteService(
    val repoRestaurante: RestauranteRepository,
    val repoCozinha: CozinhaRepository
) {

    fun salvar(restaurante: Restaurante): Restaurante {

        val cozinhaId = restaurante.cozinha.id
        val cozinha = repoCozinha.findById(cozinhaId)
            .orElseThrow { throw EntidadeNaoEncontradaException("Nao existe cozinha para id: $cozinhaId") }

        restaurante.cozinha = cozinha

        return repoRestaurante.salvar(restaurante)
    }

    fun excluir(restauranteId: Long) {
        try {
            repoRestaurante.remover(restauranteId)
        } catch (e: EmptyResultDataAccessException) {
            throw EntidadeNaoEncontradaException("Nao existe um cadastro de cozinnha com codigo: $restauranteId")
        } catch (e: DataIntegrityViolationException) {
            throw EntidadeEmUsoException("Cozinha de codigo: $restauranteId, nao pode ser removida pois esta em uso.")
        }
    }

}
