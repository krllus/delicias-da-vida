package br.com.delecias.vida.deliciasapi.api.controller

import br.com.delecias.vida.deliciasapi.api.model.CozinhasXmlWrapper
import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeEmUsoException
import br.com.delecias.vida.deliciasapi.domain.exception.EntidadeNaoEncontradaException
import br.com.delecias.vida.deliciasapi.domain.model.Cozinha
import br.com.delecias.vida.deliciasapi.domain.repository.CozinhaRepository
import br.com.delecias.vida.deliciasapi.domain.service.CadastroCozinhaService
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/cozinhas")
class CozinhaController(
    private val cozinhaRepository: CozinhaRepository,
    private val cadastroCozinhaService: CadastroCozinhaService
) {


    @GetMapping
    fun getCozinhas(): ResponseEntity<List<Cozinha>> {
        return ResponseEntity.ok(cozinhaRepository.findAll())
    }

    @GetMapping(produces = [MediaType.APPLICATION_XML_VALUE])
    fun getCozinhasXml(): ResponseEntity<CozinhasXmlWrapper> {
        return ResponseEntity.ok(CozinhasXmlWrapper(cozinhaRepository.findAll()))
    }

    @GetMapping(value = ["/{cozinhaId}"])
    fun getCozinhaPorId(@PathVariable("cozinhaId") cozinhaId: Long): ResponseEntity<Cozinha> {

        val cozinha = cozinhaRepository.findById(cozinhaId)

        if(!cozinha.isPresent)
            return ResponseEntity.notFound().build()

        return ResponseEntity.ok(cozinha.get())
    }

    @GetMapping(value = ["/{cozinhaId}"], produces = [MediaType.APPLICATION_XML_VALUE])
    fun getCozinhaPorIdXml(@PathVariable("cozinhaId") cozinhaId: Long): ResponseEntity<Cozinha> {

        val cozinha = cozinhaRepository.findById(cozinhaId)

        if(!cozinha.isPresent)
            return ResponseEntity.notFound().build()

        return ResponseEntity.ok(cozinha.get())
    }

    @PostMapping
    fun postCozinha(@RequestBody cozinha: Cozinha): ResponseEntity<Cozinha> {

        val cozinhaSalva = cadastroCozinhaService.salvar(cozinha)

        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaSalva)
    }

    @PutMapping(value = ["/{cozinhaId}"])
    fun putCozinha(
        @PathVariable("cozinhaId") cozinhaId: Long,
        @RequestBody cozinha: Cozinha
    ): ResponseEntity<Cozinha> {

        val optionalCozinha = cozinhaRepository.findById(cozinhaId)

        if(!optionalCozinha.isPresent)
            return ResponseEntity.notFound().build()

        val cozinhaAtual = optionalCozinha.get()

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id")

        val cozinhaAtualizada = cadastroCozinhaService.salvar(cozinhaAtual)

        return ResponseEntity.ok(cozinhaAtualizada)
    }

    @DeleteMapping(value = ["/{cozinhaId}"])
    fun deleteCozinha(
        @PathVariable("cozinhaId") cozinhaId: Long
    ): ResponseEntity<Cozinha> {
        return try {
            cadastroCozinhaService.excluir(cozinhaId)
            ResponseEntity.noContent().build()
        } catch (e: EntidadeNaoEncontradaException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        } catch (e: EntidadeEmUsoException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }

}