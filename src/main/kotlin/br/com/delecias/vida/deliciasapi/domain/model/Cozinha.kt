package br.com.delecias.vida.deliciasapi.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "tb_cozinha")
data class Cozinha(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = -1,
    @JsonProperty(value = "titulo") @Column(name = "nome") var nome: String = ""
)