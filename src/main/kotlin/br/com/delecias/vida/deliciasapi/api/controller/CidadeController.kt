package br.com.delecias.vida.deliciasapi.api.controller

import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeEmUsoException
import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeNaoEncontradaException
import br.com.delecias.vida.deliciasapi.domain.model.Cidade
import br.com.delecias.vida.deliciasapi.domain.repository.CidadeRepository
import br.com.delecias.vida.deliciasapi.domain.service.CadastroCidadeService
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/cidades")
class CidadeController(
    private val repoCidade: CidadeRepository,
    private val serviceCadastroCidade: CadastroCidadeService
) {

    @GetMapping
    fun getCidades(): ResponseEntity<List<Cidade>> {
        return ResponseEntity.ok(repoCidade.findAll())
    }

    @GetMapping(value = ["/{cidadeId}"])
    fun getCidadePorId(@PathVariable("cidadeId") cidadeId: Long): ResponseEntity<Cidade> {

        val optionalCidade = repoCidade.findById(cidadeId)

        if(!optionalCidade.isPresent)
            return ResponseEntity.notFound().build()

        return ResponseEntity.ok(optionalCidade.get())
    }

    @GetMapping(value = ["/{cidadeId}"], produces = [MediaType.APPLICATION_XML_VALUE])
    fun getCidadePorIdXml(@PathVariable("cidadeId") cidadeId: Long): ResponseEntity<Cidade> {

        val optionalCidade = repoCidade.findById(cidadeId)

        if(!optionalCidade.isPresent)
            return ResponseEntity.notFound().build()

        return ResponseEntity.ok(optionalCidade.get())
    }

    @PostMapping
    fun postCidade(@RequestBody cidade: Cidade): ResponseEntity<Cidade> {

        val cidadeSalva = serviceCadastroCidade.salvar(cidade)

        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva)
    }

    @PutMapping(value = ["/{cidadeId}"])
    fun putCidade(
        @PathVariable("cidadeId") cidadeId: Long,
        @RequestBody cidade: Cidade
    ): ResponseEntity<Cidade> {

        val optionalCidade = repoCidade.findById(cidadeId)

        if(!optionalCidade.isPresent)
            return ResponseEntity.notFound().build()

        val cidadeAtual = optionalCidade.get()

        BeanUtils.copyProperties(cidade, cidadeAtual, "id")

        val cidadeAtualizada = serviceCadastroCidade.salvar(cidadeAtual)

        return ResponseEntity.ok(cidadeAtualizada)
    }

    @DeleteMapping(value = ["/{cidadeId}"])
    fun deleteCidade(
        @PathVariable("cidadeId") cidadeId: Long
    ): ResponseEntity<Cidade> {
        return try {
            serviceCadastroCidade.excluir(cidadeId)
            ResponseEntity.noContent().build()
        } catch (e: EntidadeNaoEncontradaException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        } catch (e: EntidadeEmUsoException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }

}