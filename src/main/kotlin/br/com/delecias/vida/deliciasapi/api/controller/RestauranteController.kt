package br.com.delecias.vida.deliciasapi.api.controller

import br.com.delecias.vida.deliciasapi.domain.model.Restaurante
import br.com.delecias.vida.deliciasapi.domain.repository.RestauranteRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/restaurantes")
class RestauranteController(
    private val restauranteRepository: RestauranteRepository
) {

    @GetMapping
    fun listarRestaurantes(): ResponseEntity<List<Restaurante>> {
        return ResponseEntity.ok(restauranteRepository.listar())
    }

    @GetMapping(value = ["/{restauranteId}"])
    fun getRestauranteById(@PathVariable(value = "restauranteId") restauranteId: Long): ResponseEntity<Restaurante> {

        val restaurante = restauranteRepository.buscar(restauranteId) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(restaurante)
    }

}