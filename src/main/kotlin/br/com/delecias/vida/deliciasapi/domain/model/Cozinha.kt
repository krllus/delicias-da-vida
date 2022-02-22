package br.com.delecias.vida.deliciasapi.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*


@Entity
@Table(name = "tb_cozinha")
data class Cozinha(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1L,

    @Column(nullable = false)
    val nome: String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "cozinha")
    val restaurantes: List<Restaurante> = ArrayList()
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cozinha

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}