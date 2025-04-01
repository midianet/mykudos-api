package midianet.mykudos.application.usecase.user;

import lombok.RequiredArgsConstructor;
import midianet.mykudos.application.model.User;
import midianet.mykudos.infra.database.jpa.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;

import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SalvarUserUsecase {
    private final UserRepository repository;

    @Transactional
    @Secured("ROLE_ADMIN")
    public Long execute(@NonNull final In user) {
        return repository.findByEmail(user.email)
            .orElse(repository.save(User.builder()
                .name(user.name)
                .email(user.email)
                .date(LocalDateTime.now()).build())
            ).getId();
    }

    public record In(
        @NotBlank
        @Size(min = 5, max = 80)
        String name,

        @Email
        @NotNull
        @Size(min = 5, max = 80)
        String email){}

}
