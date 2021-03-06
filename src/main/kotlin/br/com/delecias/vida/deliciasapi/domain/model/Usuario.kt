package br.com.delecias.vida.deliciasapi.domain.model

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_usuario")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1L,

    @Column(nullable = false)
    var nome: String = "",

    @Column(nullable = false)
    var email: String = "",

    @Column(nullable = false)
    var senha: String = "",

    @Column(nullable = false)
    @CreationTimestamp
    var dataCadastro: LocalDateTime = LocalDateTime.now(),

    @ManyToMany
    @JoinTable(
        name = "usuario_grupo",
        joinColumns = [JoinColumn(name = "usuario_id")],
        inverseJoinColumns = [JoinColumn(name = "grupo_id")]
    )
    var grupos: List<Grupo> = ArrayList(),
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