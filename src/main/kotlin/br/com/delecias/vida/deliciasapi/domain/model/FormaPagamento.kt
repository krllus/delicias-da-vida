package br.com.delecias.vida.deliciasapi.domain.model

import javax.persistence.*

@Entity
@Table(name = "tb_forma_pagamento")
data class FormaPagamento(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = -1,
    @Column(name = "descricao") val descricao : String = ""
)