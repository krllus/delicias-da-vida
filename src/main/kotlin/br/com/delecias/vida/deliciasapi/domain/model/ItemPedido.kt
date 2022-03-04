package br.com.delecias.vida.deliciasapi.domain.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "tb_pedido_item")
data class ItemPedido(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    var produto: Produto = Produto(),

    @Column(nullable = false)
    var quantidade: Int = 0,

    @Column(nullable = false)
    var precoUnitario : BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    var precoTotal : BigDecimal = BigDecimal.ZERO,

    @Column
    var observacao : String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    var pedido: Pedido = Pedido(),
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Permissao

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}