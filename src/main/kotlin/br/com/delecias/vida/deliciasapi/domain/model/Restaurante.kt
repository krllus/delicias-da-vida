package br.com.delecias.vida.deliciasapi.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "tb_restaurante")
data class Restaurante(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1L,

    @Column(nullable = false)
    var nome: String = "",

    @Column(name = "taxa_frete", nullable = false)
    var taxaFrete: BigDecimal = BigDecimal.ZERO,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false)
    var cozinha: Cozinha = Cozinha(),

    @JsonIgnore
    @Embedded
    var endereco: Endereco = Endereco(),

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    var dataCadastro: LocalDateTime = LocalDateTime.now(),

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    var dataAtualizacao: LocalDateTime = LocalDateTime.now(),

    @ManyToMany
    @JoinTable(
        name = "restaurante_forma_pagamento",
        joinColumns = [JoinColumn(name = "restaurante_id")],
        inverseJoinColumns = [JoinColumn(name = "forma_pagamento_id")]
    )
    var formasPagamento: List<FormaPagamento> = ArrayList(),

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    var produtos: List<Produto> = ArrayList()
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Restaurante

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}