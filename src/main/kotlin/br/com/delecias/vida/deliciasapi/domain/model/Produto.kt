package br.com.delecias.vida.deliciasapi.domain.model

import java.math.BigDecimal
import javax.persistence.*


@Entity
@Table(name = "tb_produto")
data class Produto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1L,

    @Column(nullable = false)
    var nome: String = "",

    @Column(nullable = false)
    var descricao: String = "",

    @Column(nullable = false)
    var preco: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    var ativo: Boolean = true,

    @ManyToOne
    @JoinColumn(nullable = false)
    var restaurante: Restaurante = Restaurante()
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Produto

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}