package midianet.mykudos.application.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 5 , max = 40)
    @Column(nullable = false)
    private String name;

    @NotNull
    private LocalDateTime date;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false)
    private String owner;

    @Size(max = 60)
    @Column(nullable = false)
    private String winner;

    @ElementCollection
    @CollectionTable(name = "guests", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "email", unique = true, nullable = false, length = 60)
    private List<String> guests;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kudo> kudos;

}
