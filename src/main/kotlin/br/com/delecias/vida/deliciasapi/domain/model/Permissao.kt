package br.com.delecias.vida.deliciasapi.domain.model

import javax.persistence.*


@Entity
@Table(name = "tb_permissao")
data class Permissao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1L,

    @Column(nullable = false)
    var nome: String = "",

    @Column(nullable = false)
    var descricao: String = "",

    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    var grupo: Grupo = Grupo(),

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