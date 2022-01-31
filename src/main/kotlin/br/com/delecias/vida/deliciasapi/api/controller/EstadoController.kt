package br.com.delecias.vida.deliciasapi.api.controller

import br.com.delecias.vida.deliciasapi.domain.model.Estado
import br.com.delecias.vida.deliciasapi.domain.repository.EstadoRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/estados")
class EstadoController(
    val estadoRepository: EstadoRepository
) {

    @GetMapping
    fun listar(): ResponseEntity<List<Estado>> {
        return ResponseEntity.ok(estadoRepository.listar())
    }
}