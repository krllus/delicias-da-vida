package br.com.delecias.vida.deliciasapi.domain.exception

class EntidadeNaoEncontradaException(private val mensagem : String) : RuntimeException(mensagem)