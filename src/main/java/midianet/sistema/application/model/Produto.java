package midianet.sistema.application.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 5 , max = 40)
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @Column(nullable = false)
    private Grupo grupo;

    @NotNull
    @Column(nullable = false)
    private Classe classe;

    @NotNull
    @Column(nullable = false)
    private BigDecimal valor;

    @NotNull
    @Column(nullable = false)
    private BigDecimal peso;

    public enum Grupo {
        ESPORTE, ELETRONICO, CASA,
    }

    public enum Classe {
        ECONOMICO, BASICO, LUXO
    }

}