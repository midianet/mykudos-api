package midianet.mykudos.application.usecase.user;

import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.security.access.annotation.Secured;
import javax.persistence.EntityNotFoundException;

import midianet.mykudos.application.model.User;
import midianet.mykudos.infra.helper.MessageHelper;
import midianet.mykudos.infra.database.jpa.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ObterUserUsecase {
    private final MessageHelper messageHelper;
    private final UserRepository repository;

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public Out execute(@NonNull final Long id) {
        return repository.findById(id)
            .map(Out::new)
            .orElseThrow(() -> new EntityNotFoundException(
                messageHelper.getMessage("app.message.error.entity.notfound",
                                         "User", id)));
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Out {
        private Long id;
        private String name;
        private String email;
        private LocalDateTime date;

        public Out(@NonNull User user){
            this(user.getId(), user.getName(), user.getEmail(), user.getDate());
        }

        public User toEntity(){
            return User.builder()
                .id(id)
                .name(name)
                .email(email)
                .date(date).build();
        }

    }

}

