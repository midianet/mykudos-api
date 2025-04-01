package midianet.mykudos.application.usecase.user;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;

import javax.transaction.Transactional;

import midianet.mykudos.infra.database.jpa.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class DeletarUserUsecase {
    private final UserRepository repository;
    private final ObterUserUsecase obterGuest;

    @Transactional
    @Secured("ROLE_ADMIN")
    public void execute(@NonNull final Long id) {
        repository.delete(obterGuest.execute(id).toEntity());
    }

}
