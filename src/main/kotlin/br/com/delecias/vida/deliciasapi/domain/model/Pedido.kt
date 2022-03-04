package br.com.delecias.vida.deliciasapi.domain.model

import br.com.delecias.vida.deliciasapi.domain.enum.StatusPedido
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_pedido")
data class Pedido(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1L,

    @Column(nullable = false)
    var subtotal: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    var taxaFrete: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    var valorTotal: BigDecimal = BigDecimal.ZERO,

    @CreationTimestamp
    @Column(nullable = false)
    var dataCriacao: LocalDateTime = LocalDateTime.now(),

    @Column
    var dataConfirmacao: LocalDateTime = LocalDateTime.now(),

    @Column
    var dataCancelamento: LocalDateTime = LocalDateTime.now(),

    @Column
    var dataEntrega: LocalDateTime = LocalDateTime.now(),

    @Column
    var status: StatusPedido = StatusPedido.CRIADO,

    @Embedded
    var endereco: Endereco = Endereco(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id", nullable = false)
    var restaurante: Restaurante = Restaurante(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    var cliente: Usuario = Usuario(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    var formaPagamento: FormaPagamento = FormaPagamento(),

    @JsonIgnore
    @OneToMany(mappedBy = "pedido")
    val itens: List<ItemPedido> = ArrayList()

) {
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