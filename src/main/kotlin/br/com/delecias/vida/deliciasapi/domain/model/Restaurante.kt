package br.com.delecias.vida.deliciasapi.domain.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "tb_restaurante")
data class Restaurante(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = -1,
    @Column(name = "nome") val nomeRestaurante: String = "",
    @Column(name = "taxa_entrega") val taxaEntrega: BigDecimal = BigDecimal.ZERO,
    @ManyToOne val cozinha : Cozinha = Cozinha()
)