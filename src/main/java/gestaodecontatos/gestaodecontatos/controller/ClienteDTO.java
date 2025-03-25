package gestaodecontatos.gestaodecontatos.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import gestaodecontatos.gestaodecontatos.entity.Contato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ClienteDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
    private String cpf;

    @Past(message = "Data de nascimento deve ser válida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    private List<ContatoDTO> contatos;
}
