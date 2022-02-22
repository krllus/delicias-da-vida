package br.com.delecias.vida.deliciasapi.domain.model

import javax.persistence.*


@Entity
@Table(name = "tb_forma_pagamento")
data class FormaPagamento(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1L,

    @Column(nullable = false)
    var descricao: String = ""
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FormaPagamento

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}