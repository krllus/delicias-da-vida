package br.com.delecias.vida.deliciasapi.domain.exception

class EntidadeEmUsoException(private val mensagem : String) : RuntimeException(mensagem)