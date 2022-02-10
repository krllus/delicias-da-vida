package br.com.delecias.vida.deliciasapi.domain.repository

import br.com.delecias.vida.deliciasapi.domain.model.Cozinha
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CozinhaRepository : JpaRepository<Cozinha, Long>{
    fun findByNome(nome :String): List<Cozinha>
}