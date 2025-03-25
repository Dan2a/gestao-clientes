package gestaodecontatos.gestaodecontatos.controller;

import gestaodecontatos.gestaodecontatos.entity.TipoContato;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContatoDTO {
    @Id
    private Long id;

    @NotNull(message = "O tipo do contato é obrigatório")
    private TipoContato tipo;

    @NotBlank(message = "O valor do contato é obrigatório")
    private String valor;

    private String observacao;
}
