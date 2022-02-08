package br.com.delecias.vida.deliciasapi.api.model

import br.com.delecias.vida.deliciasapi.domain.model.Cozinha
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper

@JsonRootName("cozinhas")
data class CozinhasXmlWrapper(
    @JsonProperty("cozinha")
    @JacksonXmlElementWrapper(useWrapping = false)
    val cozinhas : List<Cozinha>
)