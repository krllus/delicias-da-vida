package br.com.delecias.vida.deliciasapi

import br.com.delecias.vida.deliciasapi.domain.model.Cozinha

import br.com.delecias.vida.deliciasapi.domain.repository.CozinhaRepository
import br.com.delecias.vida.deliciasapi.domain.repository.RestauranteRepository

import org.springframework.boot.WebApplicationType

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContext


fun main(args : Array<String>){
    val applicationContext: ApplicationContext = SpringApplicationBuilder(DeliciasDaVidaApplication::class.java)
        .web(WebApplicationType.NONE)
        .run(*args)

    val cozinhaRepository: CozinhaRepository = applicationContext.getBean(CozinhaRepository::class.java)

    val restauranteRepository : RestauranteRepository = applicationContext.getBean(RestauranteRepository::class.java)

    val cozinha = Cozinha(1L, "Brasileira")

    cozinhaRepository.salvar(cozinha)

    cozinhaRepository.listar().forEach { println(it) }
    restauranteRepository.listar().forEach { println(it) }

}