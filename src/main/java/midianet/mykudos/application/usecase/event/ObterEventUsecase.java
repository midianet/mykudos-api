package midianet.mykudos.application.usecase.event;

import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;
import javax.persistence.EntityNotFoundException;

import midianet.mykudos.application.model.Event;
import midianet.mykudos.infra.helper.MessageHelper;
import midianet.mykudos.infra.database.jpa.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ObterEventUsecase {
    private final MessageHelper messageHelper;
    private final EventRepository repository;

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public Out execute(@NonNull final Long id) {
        return repository.findById(id)
            .map(Out::new)
            .orElseThrow(() -> new EntityNotFoundException(
                messageHelper.getMessage("app.message.error.entity.notfound",
                                         "Event", id)));
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Out {
        private Long id;
        private String name;
        private LocalDateTime date;
        private String owner;
        private List<String> guests;
        private String winner;

        public Out(@NonNull Event event){
            this(event.getId(), event.getName(), event.getDate(), event.getOwner(), event.getGuests(), event.getWinner());
        }

        public Event toEntity(){
            return Event.builder()
                .id(id)
                .name(name)
                .date(date)
                .owner(owner)
                .guests(guests)
                .winner(winner).build();
        }

    }

}
