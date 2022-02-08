package br.com.delecias.vida.deliciasapi.api.controller

import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeNaoEncontradaException
import br.com.delecias.vida.deliciasapi.domain.model.Restaurante
import br.com.delecias.vida.deliciasapi.domain.repository.RestauranteRepository
import br.com.delecias.vida.deliciasapi.domain.service.CadastroRestauranteService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/restaurantes")
class RestauranteController(
    private val repoRestaurante: RestauranteRepository,
    private val serviceRestaurante: CadastroRestauranteService
) {

    @GetMapping
    fun listarRestaurantes(): ResponseEntity<List<Restaurante>> {
        return ResponseEntity.ok(repoRestaurante.listar())
    }

    @GetMapping(value = ["/{restauranteId}"])
    fun getRestauranteById(@PathVariable(value = "restauranteId") restauranteId: Long): ResponseEntity<Restaurante> {

        val restaurante = repoRestaurante.buscar(restauranteId) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(restaurante)
    }

    @PostMapping
    fun cadastrarRestaurante(@RequestBody restaurante: Restaurante) : ResponseEntity<Any>{
        return try{
            val restauranteCadastrado = serviceRestaurante.salvar(restaurante)
            ResponseEntity.status(HttpStatus.CREATED).body(restauranteCadastrado)
        }catch (e : EntidadeNaoEncontradaException){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

}