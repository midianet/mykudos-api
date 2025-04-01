package midianet.mykudos.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kudos")
public class Kudo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @ManyToOne
    //@JoinColumn(name = "eventId")
    private Event event;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false)
    private String voter;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false)
    private String elected;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String text;

}