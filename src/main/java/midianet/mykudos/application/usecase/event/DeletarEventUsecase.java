package midianet.mykudos.application.usecase.event;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;

import javax.transaction.Transactional;

import midianet.mykudos.infra.database.jpa.repository.EventRepository;

@Service
@RequiredArgsConstructor
public class DeletarEventUsecase {
    private final EventRepository repository;
    private final ObterEventUsecase obterEvent;

    @Transactional
    @Secured("ROLE_ADMIN")
    public void execute(@NonNull final Long id) {
        repository.delete(obterEvent.execute(id).toEntity());
    }

}
