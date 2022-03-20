package br.com.delecias.vida.deliciasapi.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Entidade em uso")
class EntidadeEmUsoException(private val mensagem : String) : RuntimeException(mensagem)