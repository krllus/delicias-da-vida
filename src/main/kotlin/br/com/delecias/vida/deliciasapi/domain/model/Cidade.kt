package br.com.delecias.vida.deliciasapi.domain.model

import javax.persistence.*

@Entity
@Table(name = "tb_cidade")
data class Cidade(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = -1,
    @Column(name = "nome") val nome : String,
    @ManyToOne val estado : Estado
)