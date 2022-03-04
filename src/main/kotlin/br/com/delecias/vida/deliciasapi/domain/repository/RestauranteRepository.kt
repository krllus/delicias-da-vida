package br.com.delecias.vida.deliciasapi.domain.repository

import br.com.delecias.vida.deliciasapi.domain.model.Restaurante
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RestauranteRepository : JpaRepository<Restaurante, Long>{

    @Query("from Restaurante r join r.cozinha left join fetch r.formasPagamento")
    override fun findAll() : List<Restaurante>

}