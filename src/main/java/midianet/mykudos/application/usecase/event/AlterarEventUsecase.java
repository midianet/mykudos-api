package midianet.mykudos.application.usecase.event;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;

import javax.transaction.Transactional;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

import midianet.mykudos.infra.database.jpa.repository.EventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlterarEventUsecase {
    private final EventRepository repository;
    private final ObterEventUsecase obterEvent;

    @Transactional
    @Secured("ROLE_ADMIN")
    public void execute(@NonNull final Long id, @NonNull final In event) {
        final var persistent = obterEvent.execute(id).toEntity();
        BeanUtils.copyProperties(event, persistent);
        repository.save(persistent);
    }

    public record In(
        @NotBlank
        @Size(min = 5, max = 40)
        String name,

        List<String> guests){}

}
