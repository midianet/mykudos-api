package midianet.mykudos.application.usecase.event;

import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.data.domain.*;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;

import midianet.mykudos.application.model.Event;
import midianet.mykudos.infra.database.jpa.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarEventUsecase {
    private final EventRepository repository;

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public Page<Out> execute(@NonNull final In example,
                             @NonNull final Pageable page) {
        final var matcher = ExampleMatcher.matchingAll()
            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith());
        final var paged = repository.findAll(Example.of(example.toEntity(), matcher), page);
        return PageableExecutionUtils.getPage(paged.getContent().stream().map(Out::new).toList(),
                                              page,
                                              paged::getTotalElements);
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class In {
        private String name;

        public Event toEntity(){
            return Event.builder()
                .name(name).build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Out {
        private Long id;
        private String name;
        private String owner;
        private LocalDateTime date;
        private List<String> guests;
        private String winner;

        public Out(@NonNull Event event){
            this(event.getId(),
                 event.getName(),
                 event.getOwner(),
                 event.getDate(),
                 event.getGuests(),
                 event.getWinner());
        }

    }

}
