package br.com.delecias.vida.deliciasapi.domain.model


import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


@Embeddable
data class Endereco(
    @Column(name = "endereco_cep")
    var cep: String = "",

    @Column(name = "endereco_logradouro")
    var logradouro: String = "",

    @Column(name = "endereco_numero")
    var numero: String = "",

    @Column(name = "endereco_complemento")
    var complemento: String = "",

    @Column(name = "endereco_bairro")
    var bairro: String = "",

    @ManyToOne
    @JoinColumn(name = "endereco_cidade_id")
    var cidade: Cidade = Cidade()
)