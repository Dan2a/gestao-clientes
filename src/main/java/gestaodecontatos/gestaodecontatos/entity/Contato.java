package gestaodecontatos.gestaodecontatos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "contatos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cliente_id", nullable = false)
    @JsonBackReference
    private Cliente cliente;

    @NotNull(message = "O tipo do contato é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoContato tipo;

    @NotBlank(message = "O valor do contato é obrigatório")
    @Column(nullable = false)
    private String valor;

    private String observacao;

}

