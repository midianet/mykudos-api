package midianet.mykudos.application.usecase.event;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;

import javax.transaction.Transactional;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

import midianet.mykudos.application.model.Event;
import midianet.mykudos.infra.database.jpa.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CriarEventUsecase {
    private final EventRepository repository;

    @Transactional
    @Secured("ROLE_ADMIN")
    public Long execute(@NonNull final In event) {
        final var owner = "midianet@gmail.com";
        final var guests = Optional.ofNullable(event.guests)
            .orElse(new ArrayList<>());
        if(event.addOwner() && guests.contains(owner)) guests.add(owner);
        return repository.save(Event.builder()
            .name(event.name)
            .date(LocalDateTime.now())
            .owner(owner)
            .guests(guests).build()).getId();
    }

    public record In(
        @NotBlank
        @Size(min = 5, max = 40)
        String name,

        List<String> guests,
        Boolean addOwner){}

}
