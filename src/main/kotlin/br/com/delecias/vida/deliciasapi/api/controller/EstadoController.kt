package br.com.delecias.vida.deliciasapi.api.controller

import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeEmUsoException
import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeNaoEncontradaException
import br.com.delecias.vida.deliciasapi.domain.model.Estado
import br.com.delecias.vida.deliciasapi.domain.repository.EstadoRepository
import br.com.delecias.vida.deliciasapi.domain.service.CadastroEstadoService
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/estados")
class EstadoController(
    private val repoEstado: EstadoRepository,
    private val serviceCadastroEstado: CadastroEstadoService
) {


    @GetMapping
    fun getEstados(): ResponseEntity<List<Estado>> {
        return ResponseEntity.ok(repoEstado.listar())
    }

    @GetMapping(value = ["/{estadoId}"])
    fun getCidadePorId(@PathVariable("estadoId") estadoId: Long): ResponseEntity<Estado> {

        val estado = repoEstado.buscar(estadoId) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(estado)
    }

    @GetMapping(value = ["/{estadoId}"], produces = [MediaType.APPLICATION_XML_VALUE])
    fun getCidadePorIdXml(@PathVariable("estadoId") estadoId: Long): ResponseEntity<Estado> {

        val estado = repoEstado.buscar(estadoId) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(estado)
    }

    @PostMapping
    fun postEstado(@RequestBody estado: Estado): ResponseEntity<Estado> {

        val estadoSalvo = serviceCadastroEstado.salvar(estado)

        return ResponseEntity.status(HttpStatus.CREATED).body(estadoSalvo)
    }

    @PutMapping(value = ["/{estadoId}"])
    fun putEstado(
        @PathVariable("estadoId") estadoId: Long,
        @RequestBody estado: Estado
    ): ResponseEntity<Estado> {

        val estadoAtual = repoEstado.buscar(estadoId) ?: return ResponseEntity.notFound().build()

        BeanUtils.copyProperties(estado, estadoAtual, "id")

        val cidadeAtualizada = serviceCadastroEstado.salvar(estadoAtual)

        return ResponseEntity.ok(cidadeAtualizada)
    }

    @DeleteMapping(value = ["/{estadoId}"])
    fun deleteEstado(
        @PathVariable("estadoId") estadoId: Long
    ): ResponseEntity<Estado> {
        return try {
            serviceCadastroEstado.excluir(estadoId)
            ResponseEntity.noContent().build()
        } catch (e: EntidadeNaoEncontradaException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        } catch (e: EntidadeEmUsoException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }

}