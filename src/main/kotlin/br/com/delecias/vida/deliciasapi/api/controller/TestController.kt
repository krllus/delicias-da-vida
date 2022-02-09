package br.com.delecias.vida.deliciasapi.api.controller

import br.com.delecias.vida.deliciasapi.domain.model.Cozinha
import br.com.delecias.vida.deliciasapi.domain.repository.CozinhaRepository
import br.com.delecias.vida.deliciasapi.domain.service.CadastroCozinhaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teste")
class TestController(
    private val cozinhaRepository: CozinhaRepository,
    private val cadastroCozinhaService: CadastroCozinhaService
) {
    @GetMapping(value = ["cozinhas/por-nome"])
    fun getCozinhas(@RequestParam("nome") nome: String): ResponseEntity<List<Cozinha>> {
        return ResponseEntity.ok(cozinhaRepository.listarCozinhasPorNome(nome))
    }
}