package br.com.delecias.vida.deliciasapi.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entidade nao encontrada")
class EntidadeNaoEncontradaException(private val mensagem : String) : RuntimeException(mensagem)