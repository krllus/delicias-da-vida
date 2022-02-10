package br.com.delecias.vida.deliciasapi.domain.repository

import br.com.delecias.vida.deliciasapi.domain.model.Permissao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PermissaoRepository : JpaRepository<Permissao, Long>