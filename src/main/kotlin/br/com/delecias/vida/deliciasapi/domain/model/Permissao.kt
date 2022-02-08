package br.com.delecias.vida.deliciasapi.domain.model

import javax.persistence.*

@Entity
@Table(name = "tb_permissao")
data class Permissao(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = -1,
    @Column(name = "nome") var nome: String = "",
    @Column(name = "descricao") var descricao : String = ""
)